package com.patient.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Product;
import com.patient.Repo.ProductRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import com.patient.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ProductRepo productRepo;

	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		
		return productRepo.save(product);
	}
	
	public Product getDoseName(Integer productId , Integer serialNumber) {
		// TODO Auto-generated method stub
		//Dose,Quantity,Status from quantity and event_desc from order_events
		Query q1=entityManager.createQuery("select p.productId,p.active,p.createdOn,p.enterpriseId,p.gtin,p.manufacturer,p.modifiedOn,p.ndc,p.packageType,p.productName,oe.quantity,oe.eventDesc,p.minimumDays from Product p inner join OrderEvents oe on oe.productId = p.productId inner join Serial s on s.productId = oe.productId where p.productId=:a and s.serialNumber=:b");
		q1.setParameter("a",productId);
		q1.setParameter("b", serialNumber);
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
		p.setMinimumDays((Integer)o[12]);
		}
//		productRepo.save(p);
		return p;
		
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> list = productRepo.findAll();
		return list;
	}

	@Override
	public List<Product> editProduct(List<Product> products) {
		// TODO Auto-generated method stub
		List<Product> list = productRepo.findAll();
		int i=0;
		int length =products.size();
		for(Product p :list) {
			if(i<length) {
				p.setMinimumDays(products.get(i).getMinimumDays());
				i=i+1;
				productRepo.save(p);
			}	
		}
		return list;
	}
}
