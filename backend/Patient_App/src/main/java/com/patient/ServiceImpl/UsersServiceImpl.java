package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patient.Entity.Users;
import com.patient.Repo.UsersRepo;
import com.patient.Service.UsersService;
@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersRepo usersRepo;

	@Override
	public Users addUsers(Users users) {
		// TODO Auto-generated method stub
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		users.setRole("CLP");
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
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		Users u=usersRepo.save(users);
		return u;
			 
		
	}
	
	public Users editUser(Users users,Integer userId)
	{
		Users u = usersRepo.findById(userId).orElseThrow();
		u.setFirstName(users.getFirstName());
		u.setLastName(users.getLastName());
		u.setEmail(users.getEmail());
		u.setMiddleName(users.getMiddleName());
		u.setUserLogin(users.getUserLogin());
		u.setJobTitle(users.getJobTitle());
		u.setActive(users.getActive());
		u.setWorkPhone(users.getWorkPhone());
		u.setMobilePhone(users.getMobilePhone());
		u.setPassword(users.getPassword());
		u.setRole(users.getRole());
		Users updatedUsers=usersRepo.save(u);
		return updatedUsers;
	}
	
	
	public List<Users> getAllUsers()
	{
		List<Users> l=usersRepo.findAll();
		return l;
	}
	
	
	public String delete(Integer userId)
	{
		usersRepo.deleteById(userId);
		return "user deleted successfully";
	}

}
