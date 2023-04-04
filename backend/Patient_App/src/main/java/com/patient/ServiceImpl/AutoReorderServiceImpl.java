package com.patient.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.AutoReorder;
import com.patient.Repo.AutoReorderRepo;
import com.patient.Service.AutoReorderService;
@Service
public class AutoReorderServiceImpl implements AutoReorderService {
	
	@Autowired
	private AutoReorderRepo autoReorderRepo;

	@Override
	public List<AutoReorder> getAll() {
		// TODO Auto-generated method stub
		List<AutoReorder> list = autoReorderRepo.findAll();
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
			i++;
			autoReorderRepo.save(a);
			}
		}
		return list;
	}

}
