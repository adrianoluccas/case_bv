package com.boavista.snack.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boavista.snack.entity.OrderPayment;

public interface OrderRepository extends BaseRepository<OrderPayment>{
	
	@Query(value = "select max(o.id) from OrderPayment o")
	Long getMaxId();
	
	@Query(value = "select sum(o.price) from OrderPayment o where o.idOrder= :iOrder")
	BigDecimal getTotalPriceByIdOrder(@Param("iOrder") Long idOrder);
}
