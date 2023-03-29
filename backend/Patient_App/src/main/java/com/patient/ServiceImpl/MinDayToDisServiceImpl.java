package com.patient.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.MinDaysToDis;
import com.patient.Repo.MinDaysToDisRepo;
import com.patient.Repo.ProductRepo;
import com.patient.Service.MinDayToDisService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class MinDayToDisServiceImpl implements MinDayToDisService {
	
	@Autowired
	private MinDaysToDisRepo minDaysToDisRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private MinDaysToDis minDaysToDis;
	
	@Override
	public List<MinDaysToDis> getAll() {
		// TODO Auto-generated method stub
		Query q = entityManager.createNativeQuery("select p.product_id,p.product_name from product p");
		List<Object[]> list = q.getResultList();
		List<MinDaysToDis> result = new ArrayList<>();
		for(Object[] o:list) {
		minDaysToDis.setProductId((Integer)o[0]);
		minDaysToDis.setProductName((String)o[1]);
		minDaysToDis.setMinimumDays(30);
		result.add(minDaysToDis);
		}
		return result;
	}

	@Override
	public MinDaysToDis create(MinDaysToDis minDaysToDis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MinDaysToDis edit(MinDaysToDis minDaysToDis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int productId) {
		// TODO Auto-generated method stub
		
	}

}
