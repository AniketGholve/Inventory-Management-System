package com.patient.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.AutoReorder;
import com.patient.Entity.ManualReorder;
import com.patient.Entity.UsageOverLastMonths;
import com.patient.Repo.AutoReorderRepo;
import com.patient.Service.AutoReorderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class AutoReorderServiceImpl implements AutoReorderService {
	
	@Autowired
	private AutoReorderRepo autoReorderRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private DispenceToPatientServiceImpl dispenceToPatientServiceImpl;

	@Override
	public List<AutoReorder> getAll() {
		Query q=entityManager.createNativeQuery("select * from auto_reorder order by product_id");

		List<Object[]> l=q.getResultList();

		List<UsageOverLastMonths> usageList=dispenceToPatientServiceImpl.getAllUsedDoses();
		System.out.println("abcd");
		System.out.println(usageList.size());
		int k=0;
		List<AutoReorder> list = new ArrayList<>();
		
		for(Object[] o:l) {
			System.out.println(Arrays.toString(o));

		AutoReorder ar=new AutoReorder();
//        ar.setProductId();
		ar.setProductName((String)o[0]);
		ar.setAddOnDose((String)o[1]);
		ar.setAddOnQuantity((Integer)o[2]);
		ar.setReorderPoint((Integer)o[3]);
		ar.setReorderQuantity((Integer)o[4]);
		ar.setProductId((Integer)o[5]);
		ar.setUsageOverLastMonths(usageList.get(k++));
		
		
		list.add(ar);

		}

//		List<ManualReorder> list = manualReorderRepo.findAll();
	//
		return list;

		}

	@Override
	public List<AutoReorder> updateReorder(List<AutoReorder> list) {
		// TODO Auto-generated method stub
		List<AutoReorder> result = new ArrayList<>();
		int i=0;
		for(AutoReorder a:list) {
			if(i<list.size()) {
			a.setAddOnDose(list.get(i).getAddOnDose());
			a.setAddOnQuantity(list.get(i).getAddOnQuantity());
			a.setReorderPoint(list.get(i).getReorderPoint());
			a.setReorderQuantity(list.get(i).getReorderQuantity());
			System.out.println(list.get(i).getProductId());
			a.setProductId(list.get(i).getProductId());
			i++;
			autoReorderRepo.save(a);
			}
		}
		return list;
	}

}
