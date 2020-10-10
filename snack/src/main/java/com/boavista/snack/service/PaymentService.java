package com.boavista.snack.service;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boavista.snack.dto.InputMoneyRequestDTO;
import com.boavista.snack.dto.InputMoneyResultDTO;
import com.boavista.snack.entity.OrderPayment;
import com.boavista.snack.repository.OrderRepository;

@Service
public class PaymentService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public InputMoneyResultDTO inputMoney(InputMoneyRequestDTO inputMoneyRequestDTO) {
		InputMoneyResultDTO resultDTO = new InputMoneyResultDTO();
		OrderPayment orderPayment;
		Long maxId = orderRepository.getMaxId();
		Long idOrder = 0L;
		if(maxId==null
				|| maxId ==0L) {
			idOrder = 1L;
			orderPayment = new OrderPayment();
			orderPayment.setIdOrder(idOrder);
			orderPayment.setPrice(inputMoneyRequestDTO.getInputedMoney());
			orderPayment.setStatus("PENDING");
		} else {
			OrderPayment payment = orderRepository.getById(maxId);
			orderPayment = populateOrderPayment(payment,inputMoneyRequestDTO);
		}		
		orderRepository.save(orderPayment);
		resultDTO.setAccepted(Boolean.TRUE);
		resultDTO.setDescripton("");
		resultDTO.setInputedMoney(inputMoneyRequestDTO.getInputedMoney());
		resultDTO.setTotalOrderMoney(orderRepository.getTotalPriceByIdOrder(orderPayment.getIdOrder()));
		resultDTO.setOrder(orderPayment.getIdOrder());
			
		
		return resultDTO;
	}

	private OrderPayment populateOrderPayment(OrderPayment payment,InputMoneyRequestDTO inputRequestDTO) {
		OrderPayment orderPayment = new OrderPayment();
		if(payment.getStatus().equals("PENDING")) {
			orderPayment.setIdOrder(payment.getIdOrder());
		} else {
			orderPayment.setIdOrder(payment.getIdOrder() + 1L);		
		}
		orderPayment.setPrice(inputRequestDTO.getInputedMoney());
		orderPayment.setStatus("PENDING");
		orderPayment.setDtCreation(GregorianCalendar.getInstance().getTime());
		orderPayment.setDtUpdate(GregorianCalendar.getInstance().getTime());
		return orderPayment;
	}
	
}
