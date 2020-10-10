package com.boavista.snack.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boavista.snack.dto.InputMoneyRequestDTO;
import com.boavista.snack.dto.InputMoneyResultDTO;
import com.boavista.snack.entity.Product;
import com.boavista.snack.repository.OrderRepository;
import com.boavista.snack.repository.ProductRepository;

@RestController
@RequestMapping("client")
@Service
public class SnackClientControler {

	@Autowired private ProductRepository productRepository;
	@Autowired private OrderRepository orderRepository;
	
	
	@RequestMapping(method = RequestMethod.GET,path="/matheus/{id}")
    @ResponseBody
	public Optional<Product> getSnacks(@PathVariable Long id) {
//		productRepository.
		return productRepository.findById(id);
		//return "<html><body><h1>API IS ON!</h1><img src='https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Stevie_Wonder_1994.jpg/220px-Stevie_Wonder_1994.jpg'></body></html>";	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody InputMoneyResultDTO inputMoney(@RequestBody InputMoneyRequestDTO inputMoneyRequestDTO) {
		
		System.out.println(orderRepository.getMaxId());
		if (!validateInputMoney(inputMoneyRequestDTO.getInputedMoney())) {
			return new InputMoneyResultDTO(Boolean.FALSE, "Cedula inválida, verifique os valores permitidos");
		}
		
		
		return null;
	}

	private Boolean validateInputMoney(BigDecimal inputedMoney) {
		Boolean result = Boolean.FALSE;
		if(inputedMoney.equals(new BigDecimal("0.01"))
				|| inputedMoney.equals(new BigDecimal("0.05"))
				|| inputedMoney.equals(new BigDecimal("0.10"))
				|| inputedMoney.equals(new BigDecimal("0.25"))
				|| inputedMoney.equals(new BigDecimal("0.50"))
				|| inputedMoney.equals(new BigDecimal("2.00"))
				|| inputedMoney.equals(new BigDecimal("5.00"))
				|| inputedMoney.equals(new BigDecimal("10.00"))) {
			result = Boolean.TRUE;
		}
		return result;
		
	}
	
}
