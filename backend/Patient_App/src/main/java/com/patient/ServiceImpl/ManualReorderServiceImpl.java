package com.patient.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.patient.Entity.ManualReorder;
import com.patient.Entity.UsageOverLastMonths;
import com.patient.Repo.ManualReorderRepo;
import com.patient.Service.ManualReorderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@Service
public class ManualReorderServiceImpl implements ManualReorderService {
	
	@Autowired
	private ManualReorderRepo manualReorderRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private DispenceToPatientServiceImpl dispenceToPatientServiceImpl;
	
	@Override
	public List<ManualReorder> update(List<ManualReorder> list) {
		// TODO Auto-generated method stub
		int i=0;
		for(ManualReorder m:list) {
			if(i<list.size()) {
			m.setAlertQuantity(list.get(i).getAlertQuantity());
			m.setLowInventoryAlerts(list.get(i).isLowInventoryAlerts());
			m.setInSystem(list.get(i).isInSystem());
			m.setEmail(list.get(i).isEmail());
			m.setProductId(list.get(i).getProductId());
			i++;
			manualReorderRepo.save(m);
			}
		}
		return list;
	}

	@Override

	public List<ManualReorder> getAll() {

	// TODO Auto-generated method stub

	Query q=entityManager.createNativeQuery("select * from manual_reorder order by product_id");

	List<Object[]> l=q.getResultList();

	List<UsageOverLastMonths> usageList=dispenceToPatientServiceImpl.getAllUsedDoses();
	System.out.println("abcd");
	System.out.println(usageList.size());
	int k=0;
	List<ManualReorder> list = new ArrayList<>();
	
	for(Object[] o:l) {
		System.out.println(Arrays.toString(o));

	ManualReorder mr=new ManualReorder();

	mr.setProductName((String)o[0]);

	mr.setAlertQuantity((Integer)o[1]);

	mr.setLowInventoryAlerts((Boolean)o[2]);

	mr.setEmail((Boolean)o[3]);

	mr.setInSystem((Boolean)o[4]);
	mr.setProductId((Integer)o[5]);

	mr.setUsageOverLastMonths(usageList.get(k++));
	
	list.add(mr);

	}

//	List<ManualReorder> list = manualReorderRepo.findAll();
//
	return list;

	}
	
	@Override
	@Transactional
//	@Scheduled(fixedRate = 3000000)//cron = "0 0 12 * * MON-FRI"
	public String ManualReorderMessage() {
		Query q = entityManager.createNativeQuery("select i.on_hand,m.product_name from \r\n"
				+ "inventory i inner join product p on i.product_id=p.product_id \r\n"
				+ "inner join manual_reorder m on m.product_name=p.product_name \r\n"
				+ "where i.on_hand<m.alert_quantity and m.low_inventory_alerts = true");
		List<Object[]> list = q.getResultList();
		if(list.size()>0) {
		System.out.println("low");
		return "Inventory is low on doses please order doses";
		}
		else {
			return null;
		}
	}

}
