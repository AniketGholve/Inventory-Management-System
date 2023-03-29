package com.patient.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Repo.ClinicRepo;
import com.patient.Service.ClinicService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
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
		clinic.setActive(true);
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
 		clinic.setRegionalManager(null);
		clinic.setSalesRep1(null);
		clinic.setSalesRep2(null);
		clinic.setShipmentMethod(null);
		clinic.setShipToName(null);
		clinic.setSrc_id(null);
		clinic.setTimeZone(null);
		Clinic cli=clinicRepo.save(clinic);
		cli.setShipTo(cli.getLocationId()+cli.getName());
		Clinic c=clinicRepo.save(cli);
		return c;
		
		
	
				
	}

	@Override
	public List<Clinic> getAllClinic() {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select c.location_id,c.account_notes,c.account_status,c.active,c.addr_line_1,c.addr_line_2,c.beep_enabled,c.bill_to,c.bill_to_name,c.city,c.contract_pricing,c.country,c.created_by,c.created_on,c.customer_number,c.deleted,c.division_manager,c.edi_enabled,c.ehr_enabled,c.email,c.enterprise_id,c.fax,c.forecast_meu,c.gln,c.loc_type_id,c.modified_by,c.modified_on,c.name,c.order_po_number,c.override_rep,c.phone,c.regional_manager,c.sales_rep1,c.sales_rep2,c.ship_to,c.ship_to_name,c.shipment_method,c.src_id,c.state,c.state_code,c.timezone,c.zipcode,max(STR_TO_DATE(co.activity_date,'%Y-%m-%dT%H:%i:%s.%f')) as lastOrderDate,max(str_to_date(dp.created_on,'%Y-%m-%dT%H:%i:%s.%f')) as lastDispense from clinic c inner join clinic_order co on c.location_id=co.location_id inner join dispense_to_patient dp on dp.location_id=co.location_id where c.deleted=? and c.active=? group by c.location_id");
		q.setParameter(1, 0);
		q.setParameter(2, 1);
		List<Object[]> l=q.getResultList();
		System.out.println("list");
		System.out.println(l);
		List<Clinic> resultList=new ArrayList<>();
		for(Object[] o:l)
		{
			Clinic c=new Clinic();
			System.out.println((Integer)o[0]);
			c.setLocationId((Integer)o[0]);
			c.setAccountNotes((String)o[1]);
			c.setAccountStatus((String)o[2]);
			c.setActive((Boolean)o[3]);
			c.setAddrLine1((String)o[4]);
			c.setAddrLine2((String)o[5]);
			c.setBeepEnabled((String)o[6]);
			c.setBillTo((String)o[7]);
			c.setBillToName((String)o[8]);
			c.setCity((String)o[9]);
			c.setContractPricing((String)o[10]);
			c.setCountry((String)o[11]);
			c.setCreatedBy((String)o[12]);
 			c.setCreatedOn((Date)o[13]);
			c.setCustomerNumber((String)o[14]);
			c.setDeleted((Boolean)o[15]);
			c.setDivisionManager((String)o[16]);
			c.setEdiEnabled((String)o[17]);
			c.setEhrEnabled((String)o[18]);
			c.setEmail((String)o[19]);
			c.setEnterpriseId((Integer)o[20]);
			c.setFax((String)o[21]);
			c.setForecastMeu((String)o[22]);
			c.setGln((String)o[23]);
			c.setLocTypeId((String)o[24]);
			c.setModifiedBy((String)o[25]);
			c.setModifiedOn((Date)o[26]);
			c.setName((String)o[27]);
			c.setOrderPoNumber((String)o[28]);
			c.setOverrideRep((String)o[29]);
			c.setPhone((String)o[30]);
			c.setRegionalManager((String)o[31]);
			c.setSalesRep1((String)o[32]);
			c.setSalesRep2((String)o[33]);
			c.setShipTo((String)o[34]);
			c.setShipToName((String)o[35]);
			c.setShipmentMethod((String)o[36]);
			c.setSrc_id((Integer)o[37]);
			c.setState((String)o[38]);
			c.setStateCode((String)o[39]);
			c.setTimeZone((String)o[40]);
			c.setzipcode((String)o[41]);
			c.setLastOrderDate((Timestamp)o[42]);
			c.setLastDispence((Timestamp)o[43]);
			resultList.add(c);
		}
		 
		return resultList;
	}

	@Override
	public Clinic updateClinic(Clinic updatedClinic) {
		// TODO Auto-generated method stub
		Clinic clinic=clinicRepo.findById(updatedClinic.getLocationId()).orElseThrow();
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		clinic.setAddrLine1(updatedClinic.getAddrLine1());
		clinic.setAddrLine2(updatedClinic.getAddrLine2());
		clinic.setCity(updatedClinic.getCity());
		clinic.setCountry(updatedClinic.getCountry());
		clinic.setEmail(updatedClinic.getEmail());
		clinic.setEnterpriseId(updatedClinic.getEnterpriseId());
		clinic.setName(updatedClinic.getName());
		clinic.setState(updatedClinic.getState());
		clinic.setStateCode(updatedClinic.getStateCode());
		clinic.setzipcode(updatedClinic.getzipcode());
		clinic.setPhone(updatedClinic.getPhone());
		clinic.setAccountNotes("accountNotes");
		clinic.setAccountStatus("none");
		clinic.setActive(updatedClinic.getActive());
		clinic.setBeepEnabled("none");
		clinic.setBillTo("none");
		clinic.setBillToName("none");
		clinic.setContractPricing("none");
		clinic.setCreatedBy("none");
		//clinic.setCreatedOn(d);
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
		clinic.setModifiedOn(d);
		clinic.setOrderPoNumber("none");
		clinic.setOverrideRep("none");
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
	public String deleteClinic(Integer locationId) {
		// TODO Auto-generated method stub
		Clinic c=clinicRepo.findById(locationId).orElseThrow();
		c.setDeleted(true);
		c.setActive(false);
		clinicRepo.save(c);
		return "User deleted successfully";
	}

	@Override
	public Clinic getClinicById(Integer locationId) {
		// TODO Auto-generated method stub
		Clinic c=clinicRepo.findById(locationId).orElseThrow();
		return c;
	}
	
	
	
	
	public List<Clinic> getClinicNamesAndId(){
		
		
		Query q=entityManager.createNativeQuery("select location_id,enterprise_id,name from clinic where deleted=? and active=?");
		q.setParameter(1, 0);
		q.setParameter(2, 1);
		List<Object[]> l=q.getResultList();
		List<Clinic> resultList=new ArrayList<>();
		for(Object[] o:l)
		{
			Clinic c=new Clinic();
			c.setLocationId((Integer)o[0]);
			c.setEnterpriseId((Integer)o[1]);
			c.setName((String)o[2]);
			resultList.add(c);
			
		}
		return resultList;
	}


		
		
		
}

	

 
