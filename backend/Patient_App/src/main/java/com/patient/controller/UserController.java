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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	public Integer addUser(@RequestBody UserEntity user) {
		System.out.println(user.toString());
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


	@PutMapping("/editUser/{username}")
	public ResponseEntity<UserEntity> editUser(@PathVariable String username, @RequestBody UserEntity user) {
		UserEntity u = userEntityRepo.findByUsername(username);
		if(user.getFirstName() != null) {
			u.setFirstName(user.getFirstName());
			System.out.println(user.getFirstName());	
		}
		if (user.getLastName()!= null) {
			u.setLastName(user.getLastName());
		}
		if (user.getDateofBirth() != null) {
			u.setDateofBirth(user.getDateofBirth());
		}
		if (user.getPhoneNo() != null) {
			u.setPhoneNo(user.getPhoneNo());
		}
		if (user.getPassword() != null) {
			u.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		UserEntity editUser = userEntityRepo.save(u);
		return ResponseEntity.ok(editUser);
	}
	
	@GetMapping("/getUserDetails/{id}")
	public ResponseEntity<UserEntity> getById(@PathVariable("id") int id) {
		UserEntity u = userEntityRepo.findById(id).orElseThrow();
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

}
