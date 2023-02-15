package com.patient.Security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.patient.Entity.UserEntity;
import com.patient.Repo.UserEntityRepo;

@Service()
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserEntityRepo userEntityRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userEntityRepo.findByUsername(username);
		User u = new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
		return u;
	}

}