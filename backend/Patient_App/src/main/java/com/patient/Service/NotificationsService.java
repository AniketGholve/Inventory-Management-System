package com.patient.Service;

import java.util.List;

import com.patient.Entity.Notifications;

public interface NotificationsService {

	public List<Notifications> getAll();
	
	public Notifications createNotification();
	
	public Notifications deleteNotification();
	
	public List<Notifications> deleteAllNotification();
	
	public void AutoOrder();
}
