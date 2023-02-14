package com.varun.LoginUser.Security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.varun.LoginUser.Entity.UserEntity;
import com.varun.LoginUser.Repository.UserRepository;

@Service()
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepo.findByUsername(username);
		User u = new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
		return u;
	}

}