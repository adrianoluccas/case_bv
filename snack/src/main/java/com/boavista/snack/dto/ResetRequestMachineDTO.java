package com.boavista.snack.dto;

public class ResetRequestMachineDTO {
	private Long idMachine;
	private Boolean money;
	private Boolean product;
	
	public Long getIdMachine() {
		return idMachine;
	}
	public void setIdMachine(Long idMachine) {
		this.idMachine = idMachine;
	}
	public Boolean getMoney() {
		return money;
	}
	public void setMoney(Boolean money) {
		this.money = money;
	}
	public Boolean getProduct() {
		return product;
	}
	public void setProduct(Boolean product) {
		this.product = product;
	}
	
	
}
