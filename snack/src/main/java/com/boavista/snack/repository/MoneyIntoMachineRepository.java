package com.boavista.snack.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boavista.snack.entity.MoneyIntoMachine;


public interface MoneyIntoMachineRepository extends BaseRepository<MoneyIntoMachine>{
	
	@Query(value = "SELECT mim FROM MoneyIntoMachine mim WHERE mim.idMachine = :iMachine AND mim.price = :iPrice")
	public MoneyIntoMachine findByIdMachineAndPrice(@Param("iMachine") Long idMachine , @Param("iPrice") BigDecimal price);
	

}
