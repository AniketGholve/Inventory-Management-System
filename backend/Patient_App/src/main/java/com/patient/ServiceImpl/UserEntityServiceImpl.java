package com.patient.ServiceImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patient.Entity.UserEntity;
import com.patient.Repo.UserEntityRepo;
import com.patient.Service.UserEntityService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class UserEntityServiceImpl implements UserEntityService {
	
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private UserEntityRepo userEntityRepo;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity findByCustomUsername(String username) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select id,password,role,username,dateof_birth,first_name,last_name,phone_no from user_entity where username=?");
		q.setParameter(1, username);
		UserEntity u=(UserEntity) q.getSingleResult();
		return u;
	}

	@Override
	public Integer addUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		System.out.println(userEntity.toString());
		String role = userEntity.getRole();
		String arr[] = { "CLP", "ELP", "ALP", "MLP" };
		boolean result = Arrays.asList(arr).contains(role);
		if (result) {
			//userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			userEntity.setPassword(userEntity.getPassword());
			userEntity.setActive(true);
			userEntity.setDeleted(false);
			userEntityRepo.save(userEntity);
			return 1;
		}
		return -1;
		
	}

	@Override
	public UserEntity editUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		System.out.println("user");
		System.out.println(userEntity.toString());
		
		UserEntity u = userEntityRepo.findByUsername(userEntity.getUsername());
		//UserEntity u=userEntityServiceImpl.findByCustomUsername(user.getUsername());
		System.out.println(u.toString());
		if(userEntity.getFirstName() != null) {
			u.setFirstName(userEntity.getFirstName());
			System.out.println(userEntity.getFirstName());	
		}
		if (userEntity.getLastName()!= null) {
			u.setLastName(userEntity.getLastName());
		}
		if (userEntity.getDateofBirth() != null) {
			u.setDateofBirth(userEntity.getDateofBirth());
		}
		if (userEntity.getPhoneNo() != null) {
			u.setPhoneNo(userEntity.getPhoneNo());
		}
		if (userEntity.getPassword() != null) {
//			u.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			u.setPassword(userEntity.getPassword());

		}
		if (userEntity.getUsername() != null) {
			u.setUsername(userEntity.getUsername());
		}
		if (userEntity.getRole() != null) {
			u.setRole(userEntity.getRole());
		}
		
		userEntityRepo.save(u);
		return u;
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userEntityRepo.deleteById(id);	
	}

	@Override
	public UserEntity findUserById(int id) {
		// TODO Auto-generated method stub
		UserEntity u = userEntityRepo.findById(id).orElseThrow();
		return u;
	}

}
