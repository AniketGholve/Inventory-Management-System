////package com.patient.ServiceImpl;
////
////import java.util.ArrayList;
//<<<<<<< HEAD
////import java.util.Arrays;
//=======
//>>>>>>> 454cc2a8928fb831333d1ddcd5cdeb3db120f0b2
////import java.util.List;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import com.patient.Entity.MinDaysToDis;
//<<<<<<< HEAD
//////import com.patient.Repo.MinDaysToDisRepo;
//=======
////import com.patient.Repo.MinDaysToDisRepo;
//>>>>>>> 454cc2a8928fb831333d1ddcd5cdeb3db120f0b2
////import com.patient.Repo.ProductRepo;
////import com.patient.Service.MinDayToDisService;
////
////import jakarta.persistence.EntityManager;
////import jakarta.persistence.Query;
////@Service
////public class MinDayToDisServiceImpl implements MinDayToDisService {
////	
////	@Autowired
//<<<<<<< HEAD
////	private EntityManager entityManager;
////	
//=======
////	private MinDaysToDisRepo minDaysToDisRepo;
////	
////	@Autowired
////	private ProductRepo productRepo;
////	
////	@Autowired
////	private EntityManager entityManager;
////	
////	@Autowired
////	private MinDaysToDis minDaysToDis;
//>>>>>>> 454cc2a8928fb831333d1ddcd5cdeb3db120f0b2
////	
////	@Override
////	public List<MinDaysToDis> getAll() {
////		// TODO Auto-generated method stub
////		Query q = entityManager.createNativeQuery("select p.product_id,p.product_name from product p");
////		List<Object[]> list = q.getResultList();
////		List<MinDaysToDis> result = new ArrayList<>();
//<<<<<<< HEAD
////		int i = 30;
////		for(Object[] o:list) {
////		MinDaysToDis minDaysToDis = new MinDaysToDis();
////		minDaysToDis.setProductId((Integer)o[0]);
////		System.out.println((Integer)o[0]);
////		minDaysToDis.setProductName((String)o[1]);
////		System.out.println((String)o[1]);
////		minDaysToDis.setMinimumDays(i);
////		i=i+15;
////		result.add(minDaysToDis);
////		}
////		System.out.println(result);
//=======
////		for(Object[] o:list) {
////		minDaysToDis.setProductId((Integer)o[0]);
////		minDaysToDis.setProductName((String)o[1]);
////		minDaysToDis.setMinimumDays(30);
////		result.add(minDaysToDis);
////		}
//>>>>>>> 454cc2a8928fb831333d1ddcd5cdeb3db120f0b2
////		return result;
////	}
////
////	@Override
////	public MinDaysToDis create(MinDaysToDis minDaysToDis) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public MinDaysToDis edit(MinDaysToDis minDaysToDis) {
////		// TODO Auto-generated method stub
//<<<<<<< HEAD
////		MinDaysToDis m = new MinDaysToDis();
////		m.setProductId(minDaysToDis.getProductId());
////		m.setProductName(minDaysToDis.getProductName());
////		m.setMinimumDays(minDaysToDis.getMinimumDays());
////		return m;
//=======
////		return null;
//>>>>>>> 454cc2a8928fb831333d1ddcd5cdeb3db120f0b2
////	}
////
////	@Override
////	public void deleteById(int productId) {
////		// TODO Auto-generated method stub
////		
////	}
////
////}
