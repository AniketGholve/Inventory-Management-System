package com.patient.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.ManualReorder;
import com.patient.Repo.ManualReorderRepo;
import com.patient.Service.ManualReorderService;
@Service
public class ManualReorderServiceImpl implements ManualReorderService {
	
	@Autowired
	private ManualReorderRepo manualReorderRepo;
	
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
		List<ManualReorder> list = manualReorderRepo.findAll();
		return list;
	}

}
