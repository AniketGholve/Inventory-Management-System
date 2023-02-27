package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Enterprises;
import com.patient.Repo.EnterpriseRepo;
import com.patient.Service.EnterpriseService;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
	
	
	@Autowired
	private EnterpriseRepo enterpriseRepo;

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
		return listEnterprises;
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

	
	
	
	
	
	
	
	
	
	
	

}
