package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Notifications;
import com.patient.ServiceImpl.NotificationsServiceImpl;

@RestController
public class NotificationsController {

	@Autowired
	private NotificationsServiceImpl notificationsServiceImpl;
	
	@GetMapping("/getAllNotifications")
	public List<Notifications> getAllNotifications()
	{
		List<Notifications> list = notificationsServiceImpl.getAll();
		return list;
	} 
	
//	@PostMapping("/createNotifications")
//	public Notifications createNotifications(Notifications notifications)
//	{
//		Notifications n = notificationsServiceImpl.createNotification(notifications);
//		return n;
//	}
}
