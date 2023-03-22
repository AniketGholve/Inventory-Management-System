package com.patient.Repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.OrderEvents;

public interface OrderEventsRepo extends JpaRepository<OrderEvents, Integer>{
	public List<OrderEvents> findByLocationIdAndEventDesc(Integer locationId,String eventDesc); 
	
	public List<OrderEvents> findByEventDescAndActivityDateBefore(String eventDesc, Date date);

//	public List<OrderEvents> findByEventDescAndActivityDateBefore(String string, Date twoDaysAgo);
}
