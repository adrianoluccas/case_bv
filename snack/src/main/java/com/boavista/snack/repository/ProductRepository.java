package com.boavista.snack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boavista.snack.entity.Product;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
	
	@Query(value = "select prod from Product prod where prod.idMachine= :iMachine")
	public List<Product> findByIdMachine (@Param("iMachine")Long idMachine);
	@Query(value = "select prod from Product prod where prod.idMachine= :iMachine and prod.id=:iId")
	public Product findByIdFromIdMachine(@Param("iId")Long id,@Param("iMachine")Long idMachine);
	
}
