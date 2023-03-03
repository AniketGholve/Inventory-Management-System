package com.patient.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.UserEntity;
import com.patient.Service.UserEntityService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class UserEntityServiceImpl implements UserEntityService {
	
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public UserEntity findByCustomUsername(String username) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select id,password,role,username,dateof_birth,first_name,last_name,phone_no from user_entity where username=?");
		q.setParameter(1, username);
		UserEntity u=(UserEntity) q.getSingleResult();
		return u;
	}

}
