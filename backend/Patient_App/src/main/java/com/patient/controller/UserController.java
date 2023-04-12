package com.patient.controller;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Orders;
import com.patient.Entity.UserEntity;
import com.patient.Repo.OrdersRepository;
import com.patient.Repo.UserEntityRepo;
import com.patient.ServiceImpl.UserEntityServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserEntityRepo userEntityRepo;
	
	@Autowired
	private UserEntityServiceImpl userEntityServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private OrdersRepository Orepo;


	@PostMapping("/addUser")
	public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity) 
	{
		userEntityServiceImpl.addUser(userEntity);
		return new ResponseEntity<UserEntity>(HttpStatus.CREATED);
	}

	
	@PutMapping("/editUser")
	public ResponseEntity<UserEntity> editUser(@RequestBody UserEntity user) 
	{
		UserEntity editUser = userEntityServiceImpl.editUser(user);
		return new ResponseEntity<UserEntity>(editUser,HttpStatus.OK);
	}
	
	@GetMapping("/getUserById/{id}")
	public UserEntity getUserById(@PathVariable int id) {
		UserEntity u = userEntityServiceImpl.findUserById(id);
		return u;
	}
	
	@GetMapping("/getUserDetails/{username}")
	public ResponseEntity<UserEntity> getByUsername(@PathVariable String username) {
		UserEntity u = userEntityRepo.findByUsername(username);
		return new ResponseEntity<UserEntity>(u,HttpStatus.OK);
	}
	
	@GetMapping("/get/{username}")
	public UserEntity getRole(@PathVariable("username") String username) {
		return userEntityRepo.findByUsername(username);
	}

	@PreAuthorize("hasAuthority('ELP')")
	@GetMapping("/ErrorOrders/{e}")
	public List<Orders> getStatus_Error(@PathVariable("e") int e) {

		return Orepo.getErrorOrders(e);
	}

	@PreAuthorize("hasAuthority('ELP')")
	@GetMapping("/SuccessOrders/{s}")
	public List<Orders> getStatus_Success(@PathVariable("s") int s) {
		return Orepo.getSuccessOrders(s);

	}
	
	@PostMapping("/deleteById/{id}")
	public void delteById(int id) {
		userEntityServiceImpl.deleteUser(id);
	}
}
 

