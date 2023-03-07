package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.OrderEvents;

public interface OrderEventsRepo extends JpaRepository<OrderEvents, Integer>{

}
