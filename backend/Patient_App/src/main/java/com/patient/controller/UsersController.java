package com.patient.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Inventory;
import com.patient.Entity.Orders;
import com.patient.Entity.UserEntity;
import com.patient.Entity.Users;
import com.patient.Repo.OrdersRepository;
import com.patient.Repo.UserEntityRepo;
import com.patient.Service.UsersService;
import com.patient.ServiceImpl.UsersServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UsersController {
	

	@Autowired
	private OrdersRepository Orepo;
	
	
	@Autowired
	private UsersServiceImpl usersServiceImpl;

	
	@PostMapping("/addUsers")
	public Integer addUser(@RequestBody Users users) {
		Integer result=usersServiceImpl.addUsers(users);
		return result;
		}
	
	
	@PutMapping("/editUsers/{userId}")
	public ResponseEntity<Users> editUser(@PathVariable Integer userId, @RequestBody Users users) {
		Users u=usersServiceImpl.editUser(users, userId);
		return new ResponseEntity<Users>(u,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Users>> getAllUsers()
	{
		List<Users> listUsers=usersServiceImpl.getAllUsers();
		return new ResponseEntity<List<Users>>(listUsers,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteUsers/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId)
	{
		
		String s=usersServiceImpl.delete(userId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
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
