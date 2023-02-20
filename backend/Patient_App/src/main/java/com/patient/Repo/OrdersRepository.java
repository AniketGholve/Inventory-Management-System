package com.patient.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.patient.Entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	
	@Query(value="select o from Orders o where o.order_Status=:e")
	public  List<Orders> getErrorOrders(@Param("e") int status);
	
//	@Query(value = "select * from Orders o where o.order_Status=:e", nativeQuery = true)
//	public <ErrorOrders> List<ErrorOrders> getErrorOrders(@Param("e") int status);
	
	@Query(value = "select o from Orders o where o.order_Status=:s")
	public List<Orders> getSuccessOrders(@Param("s") int status);

}
