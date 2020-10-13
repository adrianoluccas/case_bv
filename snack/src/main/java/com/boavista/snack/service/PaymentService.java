package com.boavista.snack.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boavista.snack.dto.BuyProductRequestDTO;
import com.boavista.snack.dto.BuyProductResponseDTO;
import com.boavista.snack.dto.CancelPaymentResponseDTO;
import com.boavista.snack.dto.InputMoneyRequestDTO;
import com.boavista.snack.dto.InputMoneyResultDTO;
import com.boavista.snack.dto.MoneyResponseDTO;
import com.boavista.snack.entity.MoneyIntoMachine;
import com.boavista.snack.entity.OrderPayment;
import com.boavista.snack.entity.Product;
import com.boavista.snack.repository.MoneyIntoMachineRepository;
import com.boavista.snack.repository.OrderRepository;
import com.boavista.snack.repository.ProductRepository;

@Service
public class PaymentService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private MoneyIntoMachineRepository moneyIntoMachineRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(value = TxType.REQUIRES_NEW)
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
			orderPayment.setIdMachine(inputMoneyRequestDTO.getIdMachine());
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
		
		MoneyIntoMachine moneyIntoMachine = moneyIntoMachineRepository.findByIdMachineAndPrice(inputMoneyRequestDTO.getIdMachine(),inputMoneyRequestDTO.getInputedMoney());
		moneyIntoMachine.setQuantity(moneyIntoMachine.getQuantity().intValue()+1);	
		moneyIntoMachineRepository.save(moneyIntoMachine);
		
		return resultDTO;
	}
	
	@Transactional(value = TxType.REQUIRES_NEW)
	public CancelPaymentResponseDTO cancelPaymentByOrder (Long idOrderPayment) {
		CancelPaymentResponseDTO result = new CancelPaymentResponseDTO();
		List<OrderPayment> listOrderPayment = orderRepository.getListByIdOrder(idOrderPayment, "PENDING");
		if(listOrderPayment==null
				|| listOrderPayment.size()<=0) {
			result.setOrderPayment(0L);
			result.setStatus("A ordem de pagamento não está pendente");
			return result;
		}
		
		for (OrderPayment orderPayment : listOrderPayment) {
			orderPayment.setStatus("CANCELLED");
			orderRepository.save(orderPayment);
			
			MoneyIntoMachine moneyIntoMachine = moneyIntoMachineRepository.findByIdMachineAndPrice(orderPayment.getIdMachine(), orderPayment.getPrice());
			moneyIntoMachine.setQuantity(moneyIntoMachine.getQuantity().intValue()-1);
			moneyIntoMachineRepository.save(moneyIntoMachine);
		}
		result.setOrderPayment(idOrderPayment);
		result.setStatus("Cancelamento da ordem de pagamento concluido");
		return result;
	}
    
	@Transactional(value = TxType.REQUIRES_NEW)
	public BuyProductResponseDTO buyProduct (BuyProductRequestDTO buyRequestDTO) {
		BuyProductResponseDTO responseDTO = new BuyProductResponseDTO();
		Product product = productRepository.findByIdFromIdMachine(buyRequestDTO.getIdProduct(), buyRequestDTO.getIdMachine());
		BigDecimal valueOrderPayment = orderRepository.getTotalPriceByIdOrder(buyRequestDTO.getIdOrderPayment());
		if(product==null) {
			responseDTO.setAccept(false);
			responseDTO.setDescription("Produto não encontrado na maquina");
			return responseDTO;
		}else {
			if(product.getQuantity().longValue()<buyRequestDTO.getQuantity()){
				responseDTO.setAccept(false);
				responseDTO.setDescription("Quantidade indisponivel na maquina");
				return responseDTO;
			} else {
				if(valueOrderPayment.floatValue()<(product.getCharge().floatValue()*buyRequestDTO.getQuantity().floatValue())) {
					responseDTO.setAccept(false);
					responseDTO.setDescription("Valor insulficiente para compra");
					return responseDTO;
				}
			}
		}
		
		product.setQuantity((short) (product.getQuantity().shortValue() - buyRequestDTO.getQuantity().shortValue()));
		productRepository.save(product);
		
		List<OrderPayment> listOrderPayment = orderRepository.getListByIdOrder(buyRequestDTO.getIdOrderPayment(), "PENDING");
		for (OrderPayment orderPayment : listOrderPayment) {
			orderPayment.setStatus("OK");
			orderRepository.save(orderPayment);
		}
		
		float valueBuy = product.getCharge().floatValue() * buyRequestDTO.getQuantity().floatValue();
		float changeBack = valueOrderPayment.floatValue() - valueBuy;
		responseDTO.setDescription(generateChangeBack(changeBack,buyRequestDTO.getIdMachine(),buyRequestDTO.getIdOrderPayment()));
		responseDTO.setAccept(true);
		
		return responseDTO;
	}
	
	public List<MoneyResponseDTO> getMoneyList(Long idMachine) {
		List<MoneyIntoMachine> listMoney = moneyIntoMachineRepository.findByIdMachine(idMachine);
		List<MoneyResponseDTO> listMoneyDTO = new ArrayList<MoneyResponseDTO>();
		for (MoneyIntoMachine moneyIntoMachine : listMoney) {
			MoneyResponseDTO dto = new MoneyResponseDTO();
			dto.setId(moneyIntoMachine.getId());
			dto.setIdMachine(moneyIntoMachine.getIdMachine());
			dto.setPrice(moneyIntoMachine.getPrice());
			dto.setQuantity(moneyIntoMachine.getQuantity());
			listMoneyDTO.add(dto);
		}
		return listMoneyDTO;
	}
	
	private String generateChangeBack(float changeBack,Long idMachine,Long idOrderPayment) {
		String result = "TROCO: ";
		float[] coins = {10.00f,5.00f,2.00f,0.50f,0.25f,0.10f,0.05f,0.01f};
		for (int i=0;i<coins.length;i++) {
			MoneyIntoMachine money = null;
			if(changeBack>=coins[i]) {
				float valueFloat = changeBack/coins[i];
				BigDecimal value = new BigDecimal(valueFloat).setScale(2, RoundingMode.HALF_UP);
				money = moneyIntoMachineRepository.findByIdMachineAndPrice(idMachine, new BigDecimal(coins[i]));
				money.setQuantity(money.getQuantity()- value.intValue());
				moneyIntoMachineRepository.save(money);
				result = result.concat(String.valueOf(coins[i])).concat(" - qtt: ").concat(String.valueOf(value.intValue()).concat("; "));
				
				OrderPayment payment = new OrderPayment();
				payment.setDtCreation(new Date());
				payment.setDtUpdate(new Date());
				payment.setIdMachine(idMachine);
				payment.setIdOrder(idOrderPayment);
				payment.setPrice(new BigDecimal(coins[i]).multiply(new BigDecimal("-1")));
				payment.setStatus("CHANGEBACK_QTT_" + value.intValue());
				orderRepository.save(payment);
				changeBack = changeBack%coins[i];
				BigDecimal bigDecimal = new BigDecimal(changeBack).setScale(2,RoundingMode.HALF_EVEN);
				changeBack = bigDecimal.floatValue();
			}
		}	
		return result;
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
		orderPayment.setIdMachine(inputRequestDTO.getIdMachine());
		return orderPayment;
	}

	public void reset(Long idMachine) {
		List<MoneyIntoMachine>listMoney = moneyIntoMachineRepository.findByIdMachine(idMachine);
		for (MoneyIntoMachine moneyIntoMachine : listMoney) {
			moneyIntoMachine.setQuantity(0);
			moneyIntoMachineRepository.save(moneyIntoMachine);
		}
	}
	
}
