//package com.patient.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.patient.Entity.MinDaysToDis;
//import com.patient.ServiceImpl.MinDayToDisServiceImpl;
//
//@RestController
//public class MinDaysToDisController {
//	
//	@Autowired
//	private MinDayToDisServiceImpl minDayToDisServiceImpl;
//	
//	@GetMapping("/getAllMin")
//	public List<MinDaysToDis> getAllMin(){
//		List<MinDaysToDis> list = minDayToDisServiceImpl.getAll();
//		return list;
//	}
//	
//	@PutMapping("/editMin")
//	public MinDaysToDis editMin(MinDaysToDis minDaysToDis ) {
//		MinDaysToDis m = minDayToDisServiceImpl.edit(minDaysToDis);
//		return m;
//	}
//	
//}
