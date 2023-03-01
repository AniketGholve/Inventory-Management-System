//package com.patient.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.patient.Entity.Inventory;
//import com.patient.Entity.UserEntity;
//import com.patient.Entity.Users;
//import com.patient.Repo.OrdersRepository;
//import com.patient.Repo.UserEntityRepo;
//import com.patient.Service.UsersService;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api")
//public class UsersController {
//	
//	
//	@Autowired
//	private UserEntityRepo user;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Autowired
//	private OrdersRepository Orepo;
//	
//	
//	
//	@PostMapping("/addUser")
//	public Integer addUser(@RequestBody Users users) {
//		System.out.println(users.toString());
//		String role = users.getRole();
//		String arr[] = { "CLP", "ELP", "ALP", "MLP" };
//		boolean result = Arrays.asList(arr).contains(role);
//		if (result) {
//			users.setPassword(passwordEncoder.encode(users.getPassword()));
//			userEntityRepo.save(users);
//			return 1;
//		}
//		return -1;
//	}
//}
