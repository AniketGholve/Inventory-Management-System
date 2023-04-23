package com.patient.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Inventory;
import com.patient.Entity.Serial;
import com.patient.ServiceImpl.InventoryServiceImpl;


@RestController
@CrossOrigin
public class InventoryController {
	

	@Autowired
	private InventoryServiceImpl inventoryServiceImpl;
	
	//@PreAuthorize("hasAuthority('CLP')")
	@PostMapping("/createInventory")
	public ResponseEntity<?> createInventory(@RequestBody Inventory inventory)
	{
		Inventory createdInventory=inventoryServiceImpl.createInventory(inventory);
		return new ResponseEntity<Inventory>(createdInventory,HttpStatus.OK);
	}
	
	//@PreAuthorize("hasAuthority('CLP')")
	@GetMapping("/getScreen")
	public ResponseEntity<?> getScreen()
	{
		System.out.println("getScreen");
		List<Inventory> l=inventoryServiceImpl.getScreen();
		System.out.println(l.toString());
		return new ResponseEntity<List<Inventory>>(l,HttpStatus.OK);
	}
	

	@GetMapping("/getSerialNumber/{pid}/{locationId}")
	public ResponseEntity<?> getSerialNumber(@PathVariable("pid") Integer pid,@PathVariable("locationId") int locationId)
	{
		List<Serial> l=inventoryServiceImpl.getSerialNumber(pid,locationId);
		return new ResponseEntity<List<Serial>>(l,HttpStatus.OK);
		
	}
	
	@GetMapping("/getExpiredSerialDetails/{pid}/{locationId}")
	public ResponseEntity<?> getExpiredSerialDetails(@PathVariable("pid") Integer pid,@PathVariable("locationId") Integer locationId)
	{
		List<Serial> l=inventoryServiceImpl.getExpiredSerialDetails(pid,locationId);
		return new ResponseEntity<List<Serial>>(l,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getInventoryByClinic/{clinicLocationId}")
	public ResponseEntity<List<Inventory>> getInventoryByClinic(@PathVariable Integer clinicLocationId)
	{
		List<Inventory> inventoryList=inventoryServiceImpl.getInventoryByClinic(clinicLocationId);
		return new ResponseEntity<List<Inventory>>(inventoryList,HttpStatus.OK);
	}
	
	
	

}
