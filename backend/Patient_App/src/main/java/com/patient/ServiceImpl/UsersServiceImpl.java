package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.patient.Entity.Users;
import com.patient.Repo.UsersRepo;
import com.patient.Service.UsersService;

public class UsersServiceImpl implements UsersService {
	
	private PasswordEncoder passwordEncoder;
	
	private UsersRepo usersRepo;

	@Override
	public Integer addUsers(Users users) {
		// TODO Auto-generated method stub
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		System.out.println(users.toString());
		String role = users.getRole();
		String arr[] = { "CLP", "ELP", "ALP", "MLP" };
		boolean result = Arrays.asList(arr).contains(role);
		users.setDeleted(false);
		users.setPromptForLocation("none");
		users.setAllowOverride(false);
		users.setCreatedOn(d);
		users.setModifiedOn(null);
		users.setExternalId("none");
		users.setPasswordUpdatedDate(null);
		users.setToken(null);
		users.setTokenExpiryTime(null);
		users.setLastLogin(null);
		users.setSrcId(null);
		
		if (result) {
			users.setPassword(passwordEncoder.encode(users.getPassword()));
			usersRepo.save(users);
			return 1;
		}
		return -1;
		
	}

}
