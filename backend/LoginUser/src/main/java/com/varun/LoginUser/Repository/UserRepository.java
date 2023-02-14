package com.varun.LoginUser.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varun.LoginUser.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUsername(String username);
	
	//<roles> List<roles> findByRole(String role);
	
}
