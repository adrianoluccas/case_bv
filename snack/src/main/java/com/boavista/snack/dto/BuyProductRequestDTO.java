package com.boavista.snack.dto;

public class BuyProductRequestDTO {
	private Long idProduct;
	private Long idOrderPayment;
	private Long idMachine;
	private Long quantity;
	
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	public Long getIdOrderPayment() {
		return idOrderPayment;
	}
	public void setIdOrderPayment(Long idOrderPayment) {
		this.idOrderPayment = idOrderPayment;
	}
	public Long getIdMachine() {
		return idMachine;
	}
	public void setIdMachine(Long idMachine) {
		this.idMachine = idMachine;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
	
}
