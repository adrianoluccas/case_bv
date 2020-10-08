package com.boavista.snack.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

//@Entity(name="order")
public class Order implements Serializable{
	@Column
	private Integer id;
	@Column(name="dt_creation")
	private Date	dtCreation;
	@Column(name="dt_update")
	private Date	dtUpdate;
	@Column
	private String status;
	@Column
	private BigDecimal	price;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDtCreation() {
		return dtCreation;
	}
	public void setDtCreation(Date dtCreation) {
		this.dtCreation = dtCreation;
	}
	public Date getDtUpdate() {
		return dtUpdate;
	}
	public void setDtUpdate(Date dtUpdate) {
		this.dtUpdate = dtUpdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
