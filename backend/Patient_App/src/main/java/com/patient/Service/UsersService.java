package com.patient.Service;

import java.util.List;

import com.patient.Entity.Users;

public interface UsersService {
	
	public Integer addUsers(Users user);
	
	public Users editUser(Users users,Integer userId);
	
	public String delete(Integer userId);
	
	public List<Users> getAllUsers();
	

	
}
