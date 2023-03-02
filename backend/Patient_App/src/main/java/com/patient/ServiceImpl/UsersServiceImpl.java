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
		Query q=entityManager.createNativeQuery("select * from users where default_location_id=?");
		q.setParameter(1, locationId);
		List<Object[]> l=q.getResultList();
		List<Users> resultList=new ArrayList<>();
		for(Object[] o:l)
		{
			Users u=new Users();
			
			u.setActive((Boolean)o[1]);
			u.setAllowOverride((Boolean)o[2]);
			u.setAllowOverride((Boolean)o[3]);
			u.setCreatedOn((Date)o[4]);
			u.setDefaultLocationId((String)o[5]);
			u.setDeleted((Boolean)o[6]);
			u.setEmail((String)o[7]);
			u.setEnterpriseId((Integer)o[8]);
			u.setExternalId((String)o[9]);
			u.setFirstName((String)o[10]);
			u.setJobTitle((String)o[11]);
			u.setLastLogin((Date)o[12]);
			u.setLastName((String)o[13]);
			u.setMiddleName((String)o[14]);
			u.setMobilePhone((String)o[15]);
			u.setModifiedOn((Date)o[16]);
			u.setPassword((String)o[17]);
			u.setPasswordUpdatedDate((Date)o[18]);
			u.setPromptForLocation((String)o[19]);
			u.setRole((String)o[20]);
			u.setSrcId((Integer)o[21]);
			u.setToken((String)o[22]);
			u.setTokenExpiryTime((String)o[23]);
			u.setUserLogin((String)o[24]);
			u.setWorkPhone((String)o[25]);
			
			resultList.add(u);	
		}
		return resultList;
		
	}

}
