package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Inventory;
import com.patient.Entity.Users;
import com.patient.Service.UsersService;

@RestController
public class UsersController {
	@Autowired
	UsersService userService;
	@GetMapping("/getUsers")
	public ResponseEntity<?> getAllUsers()
	{
		List<Users> users=userService.getAllUsers();
		return new ResponseEntity<List<Users>>(users,HttpStatus.OK);
	}
}
