package com.boavista.snack.dto;

import java.math.BigDecimal;

public class InputMoneyRequestDTO {
	private Long idMachine;
	private BigDecimal inputedMoney;
	
	public Long getIdMachine() {
		return idMachine;
	}
	public void setIdMachine(Long idMachine) {
		this.idMachine = idMachine;
	}
	public BigDecimal getInputedMoney() {
		return inputedMoney;
	}
	public void setInputedMoney(BigDecimal inputedMoney) {
		this.inputedMoney = inputedMoney;
	}
	
	
}
