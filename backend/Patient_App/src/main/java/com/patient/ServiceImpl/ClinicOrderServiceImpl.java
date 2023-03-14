package com.patient.ServiceImpl;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Entity.ClinicOrder;
import com.patient.Repo.ClinicOrderRepo;
import com.patient.Repo.ClinicRepo;
import com.patient.Service.ClinicOrderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class ClinicOrderServiceImpl implements ClinicOrderService {
	
	@Autowired
	private ClinicOrderRepo clinicOrderRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	
	@Override
	public ClinicOrder createOrder(Integer locationId) {
		// TODO Auto-generated method stub
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		Clinic clinic=clinicRepo.findById(locationId).orElseThrow();
		ClinicOrder clinicOrder=new ClinicOrder();
		clinicOrder.setActivityDate(d);
		clinicOrder.setBilltoId(123);
		clinicOrder.setBilltoName(clinic.getBillTo());
		clinicOrder.setEnterpriseId(1);
		clinicOrder.setLocationId(locationId);
		clinicOrder.setMeu(null);
		clinicOrder.setOrderDatetime(d);
		clinicOrder.setOrderId(12345);
		clinicOrder.setOrderNote(null);
		clinicOrder.setOrderStatusId(123);
		clinicOrder.setOrderType(null);
		clinicOrder.setPersonInitial(null);
		clinicOrder.setShipfromId(0);
		clinicOrder.setShiptoId(clinic.getLocationId()+clinic.getName());
		clinicOrder.setShiptoName(clinic.getName());
		clinicOrder.setSrcId(123);
		clinicOrder.setUserId(0);
		ClinicOrder c=clinicOrderRepo.save(clinicOrder);
		String formattedDate=formatter.format(d);
		String[] a=formattedDate.split(" ");
		String[] k=a[0].split("-");
		String res="";
		for(String i:k) res=res+i;
		c.setPoNumber(res+"-"+c.getOrderId());
		c=clinicOrderRepo.save(c);
		return c;
 		
	}

	@Override
	public List<ClinicOrder> getAllOrdersById(Integer locId) {
		
		Query q = entityManager.createQuery("select co from clinic_order co where co.location_id=:u");
		q.setParameter("u", locId);
		List<ClinicOrder> l = q.getResultList();
		return l;
	}

 
	

	

	

	

}
