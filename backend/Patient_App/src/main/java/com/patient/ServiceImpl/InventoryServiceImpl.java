package com.patient.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Inventory;
import com.patient.Entity.Serial;
import com.patient.Repo.InventoryRepo;
import com.patient.Service.InventoryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private InventoryRepo inventoryRepo;

	public Inventory createInventory(Inventory createdInventory) {
		
		System.out.println(createdInventory.toString());
		return inventoryRepo.save(createdInventory);
	}

	
	@Override
	public List<Serial> getSerialNumber(int productId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select * from serial where product_id=?");
		q.setParameter(1, productId);
		List<Object[]> l=q.getResultList();
		List<Serial> resultList=new ArrayList<>();
		System.out.println(l.get(0));
		for (Object[] o:l)
		{
			Serial s=new Serial();
			s.setSerialId((Integer) o[0]);
			s.setCreatedOn((Date) o[1]);
			s.setEnterpriseId((Integer) o[2]);
			s.setExpiryDate((Date) o[3]);
			s.setLocationId((Integer) o[4]);
			s.setLot((Integer) o[5]);
			s.setNdc((Integer) o[6]);
			s.setPatientSpecific((String) o[7]);
			s.setProductId((Integer) o[8]);
			s.setSerialNumber((Integer) o[9]);
			s.setSerialStatus((Boolean) o[10]);
			s.setSrcId((Integer) o[11]);
			resultList.add(s);
			
			
		}
		
		//System.out.println(l.toString());
		return resultList;
	}

	@Override
	public List<Inventory> getScreen() {
		// TODO Auto-generated method stub
		List<Inventory> resultList=new ArrayList<>();
		Query q=entityManager.createNativeQuery("select i.product_id,p.product_name,i.expired,i.on_hand from inventory i inner join product p on i.product_id=p.product_id");
		List<Object[]> l=q.getResultList();
		for(Object[] result:l)
		{
			Inventory i=new Inventory();
			i.setProductId(result[0]==null?null:(Integer) result[0]);
			i.setProductName(result[1]==null?null:(String) result[1]);
			i.setExpiredQty(result[2]==null?null:(Integer) result[2]);
			i.setOnHand(result[3]==null?null:(Integer) result[3]);
			resultList.add(i);
			
		}
		return resultList;
		
		
		//return null;
	}

	@Override
	public List<Serial> getExpiredSerialDetails(int productId) {
		// TODO Auto-generated method stub
		
		Query q=entityManager.createNativeQuery("select * from serial where product_id=? and expiry_date<sysdate()");
		q.setParameter(1, productId);
		List<Object[]> l=q.getResultList();
		
		
		List<Serial> resultList=new ArrayList<>();
		for(Object[] o:l)
		{
			Serial s=new Serial();
			s.setSerialId((Integer) o[0]);
			s.setCreatedOn((Date) o[1]);
			s.setEnterpriseId((Integer) o[2]);
			s.setExpiryDate((Date) o[3]);
			s.setLocationId((Integer) o[4]);
			s.setLot((Integer) o[5]);
			s.setNdc((Integer) o[6]);
			s.setPatientSpecific((String) o[7]);
			s.setProductId((Integer) o[8]);
			s.setSerialNumber((Integer) o[9]);
			s.setSerialStatus((Boolean) o[10]);
			s.setSrcId((Integer) o[11]);
			resultList.add(s);
			
		}
		return resultList;
	}
	
	
	
	

}
