package com.patient.Service;

import com.patient.Entity.UserEntity;

public interface UserEntityService {
	
	
	public UserEntity findByCustomUsername(String username);
	
	public Integer addUser(UserEntity userEntity);
	
	public UserEntity editUser(UserEntity userEntity);
	
	public void deleteUser(int id);
}
