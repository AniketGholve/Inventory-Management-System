package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patient.Entity.Users;
import com.patient.Repo.UsersRepo;
import com.patient.Service.UsersService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private UsersRepo usersRepo;

	@Override
	public Users addUsers(Users users) {
		// TODO Auto-generated method stub
		System.out.println(users);
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
	
	
	public List<Users> getUsersByLocationId(Integer locationId)
	{
		System.out.println(locationId.getClass());
		System.out.println(locationId);
		Query q=entityManager.createNativeQuery("select * from users where default_location_id=?");
		q.setParameter(1, locationId);
		List<Object[]> l=q.getResultList();
		List<Users> resultList=new ArrayList<>();
		for(Object[] o:l)
		{
			Users u=new Users();
			u.setUserId((Integer)o[0]);
			u.setActive((Boolean)o[1]);
			u.setAllowOverride((Boolean)o[2]);
			u.setCreatedOn((Date)o[3]);
			u.setDefaultLocationId((Integer)o[4]);
			u.setDeleted((Boolean)o[5]);
			u.setEmail((String)o[6]);
			u.setEnterpriseId((Integer)o[7]);
			u.setExternalId((String)o[8]);
			u.setFirstName((String)o[9]);
			u.setJobTitle((String)o[10]);
			u.setLastLogin((Date)o[11]);
			u.setLastName((String)o[12]);
			u.setMiddleName((String)o[13]);
			u.setMobilePhone((String)o[14]);
			u.setModifiedOn((Date)o[15]);
			u.setPassword((String)o[16]);
			u.setPasswordUpdatedDate((Date)o[17]);
			u.setPromptForLocation((String)o[18]);
			u.setRole((String)o[19]);
			u.setSrcId((Integer)o[20]);
			u.setToken((String)o[21]);
			u.setTokenExpiryTime((String)o[22]);
			u.setUserLogin((String)o[23]);
			u.setWorkPhone((String)o[24]);
		
			resultList.add(u);	
		}
		return resultList;
		
	}

	@Override
	public Users getUsersByUsersId(Integer UsersId) {
		// TODO Auto-generated method stub
		
		Users u=usersRepo.findById(UsersId).orElseThrow();
		
		return u;
	}

}
