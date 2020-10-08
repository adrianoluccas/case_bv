package com.boavista.snack.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="money_into_machine")
public class MoneyIntoMachine implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer	id;
	@Column
	private BigDecimal	price;
	@Column
	private Integer	quantity;
	@Column(name="id_machine")
	private Integer	idMachine;
	
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
	public Integer getIdMachine() {
		return idMachine;
	}
	public void setIdMachine(Integer idMachine) {
		this.idMachine = idMachine;
	}
	
	
}
