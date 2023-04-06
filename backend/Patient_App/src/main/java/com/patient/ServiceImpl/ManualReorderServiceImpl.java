package com.patient.ServiceImpl;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.ManualReorder;
import com.patient.Entity.UsageOverLastMonths;
import com.patient.Repo.ManualReorderRepo;
import com.patient.Service.ManualReorderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class ManualReorderServiceImpl implements ManualReorderService {
	
	@Autowired
	private ManualReorderRepo manualReorderRepo;
	@Autowired
	private DispenceToPatientServiceImpl dispenceToPatientServiceImpl;
	@Autowired
	private EntityManager entityManager;
	
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
			i++;
			manualReorderRepo.save(m);
			}
		}
		return list;
	}

	@Override
	public List<ManualReorder> getAll() {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select * from manual_reorder");
		List<Object[]> l=q.getResultList();
		List<UsageOverLastMonths> usageList=dispenceToPatientServiceImpl.getAllUsedDoses();
		int k=0;
		for(Object[] o:l) {
			ManualReorder mr=new ManualReorder();
			mr.setProductName((String)o[0]);
			mr.setAlertQuantity((Integer)o[1]);
			mr.setLowInventoryAlerts((Boolean)o[2]);
			mr.setEmail((Boolean)o[3]);
			mr.setInSystem((Boolean)o[4]);
			mr.setUsageOverLastMonths(usageList.get(k++));
			
		}
		List<ManualReorder> list = manualReorderRepo.findAll();
		return list;
	}

}
