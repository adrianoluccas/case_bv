package com.boavista.snack.repository;

import org.springframework.data.jpa.repository.Query;

import com.boavista.snack.entity.OrderPayment;

public interface OrderRepository extends BaseRepository<OrderPayment>{
	
	@Query(value = "select max(o.id) from OrderPayment o")
	Long getMaxId();
	
}
