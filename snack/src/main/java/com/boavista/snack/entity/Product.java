package com.boavista.snack.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column
	private String 	name;
	@Column
	private Short	quantity;
	@Column
	private BigDecimal	charge;
	@Column(name="id_machine")
	private Integer	idMachine;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getQuantity() {
		return quantity;
	}
	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public Integer getIdMachine() {
		return idMachine;
	}
	public void setIdMachine(Integer idMachine) {
		this.idMachine = idMachine;
	}
	
	
}
