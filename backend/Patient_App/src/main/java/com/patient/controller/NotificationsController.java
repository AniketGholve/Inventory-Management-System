package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Notifications;
import com.patient.ServiceImpl.NotificationsServiceImpl;

@RestController
@CrossOrigin
public class NotificationsController {

	@Autowired
	private NotificationsServiceImpl notificationsServiceImpl;
	
	@PostMapping("/deleteAllNotification")
	public void deleteAllNotification (){
		notificationsServiceImpl.deleteAllNotification();
	}
	
	@GetMapping("/getAllNotifications")
	public List<Notifications> getAllNotifications()
	{
		List<Notifications> list = notificationsServiceImpl.getAll();
		return list;
	} 
	

	
}

