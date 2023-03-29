package com.patient.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Product;
import com.patient.Repo.ProductRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


@Service
public class ProductServiceImpl {
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ProductRepo productRepo;

	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		
		return productRepo.save(product);
	}
	
	public Product getDoseName(Integer productId) {
		// TODO Auto-generated method stub
		//Dose,Quantity,Status from quantity and event_desc from order_events
		Query q1=entityManager.createQuery("select p.productId,p.active,p.createdOn,p.enterpriseId,p.gtin,p.manufacturer,p.modifiedOn,p.ndc,p.packageType,p.productName,oe.quantity,oe.eventDesc from Product p inner join OrderEvents oe on oe.productId = p.productId where p.productId=:a");
		q1.setParameter("a",productId);
		List<Object[]> l = q1.getResultList();
		Product p = new Product();
		for(Object[] o:l) {
		p.setProductId((Integer) o[0]);
		p.setActive((Boolean)o[1]);
		p.setCreatedOn((Timestamp)o[2]);
		p.setEnterpriseId((Integer)o[3]);
		p.setGtin((String)o[4]);
		p.setManufacturer((String)o[5]);
		p.setModifiedOn((Timestamp)o[6]);
		p.setNdc((Integer)o[7]);
		p.setPackageType((String)o[8]);
		p.setProductName((String)o[9]);
		p.setQuantity((Integer)o[10]);
		p.setStatus((String)o[11]);
		}
		productRepo.save(p);
		return p;
		
	}
}
