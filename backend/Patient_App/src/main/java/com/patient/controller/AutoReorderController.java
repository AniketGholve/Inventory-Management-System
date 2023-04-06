package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.AutoReorder;
import com.patient.ServiceImpl.AutoReorderServiceImpl;

@RestController
@CrossOrigin
public class AutoReorderController {
	
	@Autowired
	private AutoReorderServiceImpl autoReorderServiceImpl;
	
	@GetMapping("/getAutoReorderDose")
	public List<AutoReorder> getAutoReorder(){
		List<AutoReorder> l = autoReorderServiceImpl.getAll();
		return l;
	}
	
	@PutMapping("/updateAutoReorder")
	public List<AutoReorder> updateAutoReorder(@RequestBody List<AutoReorder> list){
		List<AutoReorder> l = autoReorderServiceImpl.updateReorder(list);
		return l;
	}
}
