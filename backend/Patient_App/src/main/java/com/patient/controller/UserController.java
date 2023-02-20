package com.patient.controller;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Orders;
import com.patient.Entity.UserEntity;
import com.patient.Repo.OrdersRepository;
import com.patient.Repo.UserEntityRepo;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserEntityRepo userEntityRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private OrdersRepository Orepo;
	
	

	public UserController(UserEntityRepo userEntityRepo, PasswordEncoder passwordEncoder, OrdersRepository orepo) {
		super();
		this.userEntityRepo = userEntityRepo;
		this.passwordEncoder = passwordEncoder;
		this.Orepo = orepo;
	}

	@PostMapping("/addUser")
	public String addUser(@RequestBody UserEntity user) {
		String role = user.getRole();
		String arr[] = { "CLP", "ELP", "ALP", "MLP" };
		boolean result = Arrays.asList(arr).contains(role);
		if (result) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userEntityRepo.save(user);
			return "User data added sucessfully";
		}
		return "wrong role";
	}

	@GetMapping("/get")
	public String get() {
		// return userRepository.findAll();
		return "Loged in";
	}

	@GetMapping("/get/{username}")
	public UserEntity getRole(@PathVariable("username") String username) {
		return userEntityRepo.findByUsername(username);
	}
	
	@GetMapping("/ErrorOrders/{e}")
	public List<Orders> getStatus_Error(@PathVariable("e") int e){

		return Orepo.getErrorOrders(e);
	}
	
	@GetMapping("/SuccessOrders/{s}")
	public List<Orders> getStatus_Success(@PathVariable("s") int s) {
		return Orepo.getSuccessOrders(s);
		
	}

}


