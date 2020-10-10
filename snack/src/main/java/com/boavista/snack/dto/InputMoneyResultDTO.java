package com.boavista.snack.dto;

import java.math.BigDecimal;

public class InputMoneyResultDTO {
	private BigDecimal inputedMoney;
	private BigDecimal totalOrderMoney;
	private Long order;
	private Boolean accepted;
	private String descripton;
	
	public InputMoneyResultDTO(Boolean accepted, String description) {
		this.accepted=accepted;
		this.descripton=description;
	}
	
	public InputMoneyResultDTO() {
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getInputedMoney() {
		return inputedMoney;
	}
	public void setInputedMoney(BigDecimal inputedMoney) {
		this.inputedMoney = inputedMoney;
	}
	public BigDecimal getTotalOrderMoney() {
		return totalOrderMoney;
	}
	public void setTotalOrderMoney(BigDecimal totalOrderMoney) {
		this.totalOrderMoney = totalOrderMoney;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	public String getDescripton() {
		return descripton;
	}
	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}
	
	
}
