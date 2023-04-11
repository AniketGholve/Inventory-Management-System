package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Enterprises;
import com.patient.Entity.UserEntity;
import com.patient.Repo.EnterpriseRepo;
import com.patient.Service.EnterpriseService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
	
	
	@Autowired
	private EnterpriseRepo enterpriseRepo;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Enterprises createEnterprises(Enterprises enterprises) {
		// TODO Auto-generated method stub
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		enterprises.setCreatedOn(d);
		enterprises.setCreatedBy("unknown");
		enterprises.setDeleted(false);
		enterprises.setExtEnterpriseId("100");
		enterprises.setGln("100");
		enterprises.setModifiedBy("unknown");
		enterprises.setShowJit("unknown");
		Enterprises e=enterpriseRepo.save(enterprises);
		return e;
	}

	@Override
	public List<Enterprises> getAllEnterprises() {
		// TODO Auto-generated method stub
		List<Enterprises> listEnterprises=enterpriseRepo.findAll();
		List<Enterprises> list = new ArrayList<>();
		int len = listEnterprises.size();
		if(len>0)
		{
			for(Enterprises e:listEnterprises) {
				Query q1 = entityManager.createNativeQuery("select * from facility where enterprise_Id=?");
				q1.setParameter(1, e.getEnterpriseId());
				e.setFacilityCount(q1.getResultList().size());
				Query q2 = entityManager.createNativeQuery("select * from user_entity ");
//				q2.setParameter(1, e.getEnterpriseId());
//				q2.setParameter(2, e.getActive());
				e.setUsers(q2.getResultList().size());
				list.add(e);
				len--;
			}
		}
		return list;
	}

	@Override
	public Enterprises updateEnterprise(Enterprises enterprise) {
		// TODO Auto-generated method stub
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		Enterprises updatedEnterprises=enterpriseRepo.findById(enterprise.getEnterpriseId()).orElseThrow();
		updatedEnterprises.setActive(enterprise.getActive());
		updatedEnterprises.setAddrLink1(enterprise.getAddrLink1());
		updatedEnterprises.setAddrLink2(enterprise.getAddrLink2());
		updatedEnterprises.setCity(enterprise.getCity());
		updatedEnterprises.setCountry(enterprise.getCountry());
//		updatedEnterprises.setCreatedBy("unknown");
//		updatedEnterprises.setCreatedOn(d);
		updatedEnterprises.setDeleted(false);
		updatedEnterprises.setEhrImplementationStatus(enterprise.getEhrImplementationStatus());
		updatedEnterprises.setEmail(enterprise.getEmail());
		updatedEnterprises.setExtEnterpriseId("100");
		updatedEnterprises.setGln("100");
		updatedEnterprises.setIsCorporate(enterprise.getIsCorporate());
		updatedEnterprises.setModifiedBy("unknown");
		updatedEnterprises.setModifiedOn(d);
		updatedEnterprises.setName(enterprise.getName());
		updatedEnterprises.setPhone(enterprise.getPhone());
		updatedEnterprises.setScheduledReports(enterprise.getScheduledReports());
		updatedEnterprises.setSharedPatients(enterprise.getSharedPatients());
		updatedEnterprises.setShowJit("unknown");
		updatedEnterprises.setState(enterprise.getState());
		updatedEnterprises.setStateCode(enterprise.getStateCode());
		updatedEnterprises.setZipcode(enterprise.getZipcode());
		Enterprises e=enterpriseRepo.save(updatedEnterprises);
		return e;
	}

	@Override
	public String deleteEnterprise(Integer enterpriseId) {
		// TODO Auto-generated method stub
		enterpriseRepo.deleteById(enterpriseId);
		return "Enterprises Deleted Successfully";
	}

	public Enterprises getByEnterpriseId(int id) {
		Enterprises e=enterpriseRepo.findById(id).orElse(null);
		return e;
	}

//	@Override
//	public List<UserEntity> getAllUsersByEnterpriseId(int id) {
//		// TODO Auto-generated method stub
//		Query q = entityManager.createNativeQuery("select * from user_entity where enterprise_id=?");
//		q.setParameter(1, id);
//		List<Object[]> list = q.getResultList();
//		List<UserEntity> result = new ArrayList<>();
//		for(Object[] o:list) {
//			UserEntity u = new UserEntity();
//			u.set
//		}
//		return null;
//	}

	 

	 

	
	
	
	
	
	
	
	
	
	
	

}
