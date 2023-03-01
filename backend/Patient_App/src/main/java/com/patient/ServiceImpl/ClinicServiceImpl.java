package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.patient.Entity.Clinic;
import com.patient.Repo.ClinicRepo;
import com.patient.Repo.EnterpriseRepo;
import com.patient.Service.ClinicService;

import jakarta.persistence.EntityManager;

public class ClinicServiceImpl implements ClinicService {
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Clinic createClinic(Clinic clinic) {
		// TODO Auto-generated method stub
		
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		clinic.setAccountNotes("accountNotes");
		clinic.setAccountStatus("none");
		clinic.setActive(false);
		clinic.setBeepEnabled("none");
		clinic.setBillTo("none");
		clinic.setBillToName("none");
		clinic.setContractPricing("none");
		clinic.setCreatedBy("none");
		clinic.setCreatedOn(d);
		clinic.setCustomerNumber("none");
		clinic.setDeleted(false);
		clinic.setDivisionManager("none");
		clinic.setEdiEnabled("none");
		clinic.setEhrEnabled("none");
		clinic.setFax("none");
		clinic.setForecastMeu("none");
		clinic.setGln("none");
		clinic.setLocTypeId("none");
		clinic.setModifiedBy(null);
		clinic.setModifiedOn(null);
		clinic.setOrderPoNumber("none");
		clinic.setOverrideRep("none");
		clinic.setPhone("none");
		clinic.setRegionalManager(null);
		clinic.setSalesRep1(null);
		clinic.setSalesRep2(null);
		clinic.setShipmentMethod(null);
		clinic.setShipTo(null);
		clinic.setShipToName(null);
		clinic.setSrc_id(null);
		clinic.setTimeZone(null);
		Clinic c=clinicRepo.save(clinic);
		return c;
		
		
	
				
	}

	@Override
	public List<Clinic> getAllClinic() {
		// TODO Auto-generated method stub
		List<Clinic> c=clinicRepo.findAll();
		return c;
	}

	@Override
	public Clinic updateClinic(Clinic clinic) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String deleteClinic(Integer locationId) {
		// TODO Auto-generated method stub
		clinicRepo.deleteById(locationId);
		return "User deleted successfully";
	}

	@Override
	public Clinic getClinicById(Integer locationId) {
		// TODO Auto-generated method stub
		Clinic c=clinicRepo.findById(locationId).orElseThrow();
		return c;
	}

	

}
