package com.patient.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.UserEntity;
import com.patient.Repo.UserEntityRepo;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserEntityRepo userEntityRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/addUser")
	public Integer addUser(@RequestBody UserEntity user) {
		String role = user.getRole();
		String arr[] = { "CLP", "ELP", "ALP", "MLP" };
		boolean result = Arrays.asList(arr).contains(role);
		if (result) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userEntityRepo.save(user);
			return 1;
		}
		return -1;
	}

	
	
	@GetMapping("/get/{username}")
	public UserEntity getRole(@PathVariable("username") String username) {
		return userEntityRepo.findByUsername(username);
	}

}


