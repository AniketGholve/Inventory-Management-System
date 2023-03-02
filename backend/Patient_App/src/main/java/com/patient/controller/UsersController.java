package com.patient.controller;

 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.patient.Entity.Users;
import com.patient.ServiceImpl.UsersServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UsersController {
	
	@Autowired
	private UsersServiceImpl usersServiceImpl;

	
	@PostMapping("/addUsers")
	public ResponseEntity<Users> addUser(@RequestBody Users users) {
		Users u=usersServiceImpl.addUsers(users);
		return new ResponseEntity<Users>(u,HttpStatus.OK);
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
	
	
	@GetMapping("/getUsersByLocationId/{locationId}")
	public ResponseEntity<List<Users>> getUsersByLocationId(@PathVariable Integer locationId)
	{
		List<Users> l=usersServiceImpl.getUsersByLocationId(locationId);
		return new ResponseEntity<List<Users>>(l,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteUsers/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId)
	{
		
		String s=usersServiceImpl.delete(userId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	
		
	 
	
}
