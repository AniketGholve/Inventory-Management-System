package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.UserEntity;

public interface UserEntityRepo extends JpaRepository<UserEntity, Integer> {
	
	UserEntity findByUsername(String username);

}
