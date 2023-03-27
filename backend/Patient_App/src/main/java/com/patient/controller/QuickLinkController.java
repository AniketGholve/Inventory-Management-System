package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.QuickLink;
import com.patient.ServiceImpl.QuickLinkServiceImpl;

@CrossOrigin
@RestController
public class QuickLinkController {
	
	@Autowired
	QuickLinkServiceImpl quickLinkServiceImpl;
	
	@PostMapping("/addLink")
	public ResponseEntity<QuickLink> addLink(@RequestBody QuickLink quickLink)
	{
		QuickLink q = quickLinkServiceImpl.addQuickLink(quickLink);
		return new ResponseEntity<QuickLink>(q,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAllQuickLink")
	public ResponseEntity<List<QuickLink>> getAllQuickLink()
	{
		List<QuickLink> list = quickLinkServiceImpl.getAllQuickLink();
		return new ResponseEntity<List<QuickLink>>(list,HttpStatus.ACCEPTED);	
	}
	
	@PutMapping("/updateQuickLink")
	public ResponseEntity<QuickLink> updateQuickLink(@RequestBody QuickLink quickLink)
	{
		QuickLink q = quickLinkServiceImpl.updateQuickLink(quickLink);
		return new ResponseEntity<QuickLink>(q,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteQucikLink/{id}")
	public String deleteQuickLink(@PathVariable("id") int id)
	{
		String q = quickLinkServiceImpl.deleteQuickLink(id);
		return q;
	}
	
	@GetMapping("/getQuickLink/{id}")
	public ResponseEntity<QuickLink> getQuickLInk(@PathVariable int id)
	{
		QuickLink q = quickLinkServiceImpl.getQuickLink(id);
		return new ResponseEntity<QuickLink>(q,HttpStatus.OK);
	}
}
