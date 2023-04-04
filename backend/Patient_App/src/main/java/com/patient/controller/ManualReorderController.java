package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.ManualReorder;
import com.patient.ServiceImpl.ManualReorderServiceImpl;

@RestController
@CrossOrigin
public class ManualReorderController {
	
	@Autowired
	private ManualReorderServiceImpl manualReorderServiceImpl;
	
	@GetMapping("/getManualReorder")
	public List<ManualReorder> getManualReorder()
	{
		List<ManualReorder> list = manualReorderServiceImpl.getAll();
		return list;
	}
	
	@PutMapping("/updateManualReorder")
	public List<ManualReorder> updateReorder(List<ManualReorder> list)
	{
		List<ManualReorder> l = manualReorderServiceImpl.update(list);
		return l;
	}
}
