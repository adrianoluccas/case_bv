package com.boavista.snack.dto;

import java.math.BigDecimal;

import javax.persistence.Column;

public class MoneyResponseDTO {
	private Integer	id;
	private BigDecimal	price;
	private Integer	quantity;
	private Long	idMachine;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getIdMachine() {
		return idMachine;
	}
	public void setIdMachine(Long idMachine) {
		this.idMachine = idMachine;
	}
	
	
	
}
