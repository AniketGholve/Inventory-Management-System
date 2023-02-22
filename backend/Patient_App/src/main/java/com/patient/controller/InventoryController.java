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
	
	@PreAuthorize("hasAuthority('MLP')")
	@PostMapping("/createInventory")
	public ResponseEntity<?> createInventory(@RequestBody Inventory inventory)
	{
		Inventory createdInventory=inventoryServiceImpl.createInventory(inventory);
		return new ResponseEntity<Inventory>(createdInventory,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('MLP')")
	@GetMapping("/getScreen")
	public ResponseEntity<?> getScreen()
	{
		System.out.println("getScreen");
		List<Inventory> l=inventoryServiceImpl.getScreen();
		System.out.println(l.toString());
		return new ResponseEntity<List<Inventory>>(l,HttpStatus.OK);
	}
	

	@GetMapping("/getSerialNumber/{pid}")
	public ResponseEntity<?> getSerialNumber(@PathVariable("pid") Integer pid)
	{
		List<Serial> l=inventoryServiceImpl.getSerialNumber(pid);
		return new ResponseEntity<List<Serial>>(l,HttpStatus.OK);
		
	}
	
	@GetMapping("/getExpiredSerialDetails/{pid}")
	public ResponseEntity<?> getExpiredSerialDetails(@PathVariable("pid") Integer pid)
	{
		List<Serial> l=inventoryServiceImpl.getExpiredSerialDetails(pid);
		return new ResponseEntity<List<Serial>>(l,HttpStatus.OK);
		
	}

}
