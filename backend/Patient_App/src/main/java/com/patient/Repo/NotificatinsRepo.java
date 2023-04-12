package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Notifications;

public interface NotificatinsRepo extends JpaRepository<Notifications,Integer> {

}
