package com.boavista.snack.dto;

public class CancelPaymentResponseDTO {
	private Long orderPayment;
	private String status;
	
	public Long getOrderPayment() {
		return orderPayment;
	}
	public void setOrderPayment(Long orderPayment) {
		this.orderPayment = orderPayment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
