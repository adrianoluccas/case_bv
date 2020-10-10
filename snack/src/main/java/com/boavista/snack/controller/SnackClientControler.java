package com.boavista.snack.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boavista.snack.dto.InputMoneyRequestDTO;
import com.boavista.snack.dto.InputMoneyResultDTO;
import com.boavista.snack.dto.ProductResponseDTO;
import com.boavista.snack.entity.Product;
import com.boavista.snack.service.PaymentService;
import com.boavista.snack.service.ProductService;

@RestController
@RequestMapping("client")
public class SnackClientControler {

	@Autowired
	PaymentService paymentService;
	@Autowired
	ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String getSnacks() {

		 return "<html><body><h1>API IS ON!</h1><img src='https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Stevie_Wonder_1994.jpg/220px-Stevie_Wonder_1994.jpg'></body></html>";
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody InputMoneyResultDTO inputMoney(@RequestBody InputMoneyRequestDTO inputMoneyRequestDTO) {
		if (!validateInputMoney(inputMoneyRequestDTO.getInputedMoney())) {
			return new InputMoneyResultDTO(Boolean.FALSE, "Cedula inválida, verifique os valores permitidos");
		}
		return paymentService.inputMoney(inputMoneyRequestDTO);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/snacks/{idMachine}")
	public @ResponseBody List<ProductResponseDTO> productsIntoMachine(@PathVariable Long idMachine) {
		return productService.getProductList(idMachine);
	}

	private Boolean validateInputMoney(BigDecimal inputedMoney) {
		Boolean result = Boolean.FALSE;
		if (inputedMoney.equals(new BigDecimal("0.01")) || inputedMoney.equals(new BigDecimal("0.05"))
				|| inputedMoney.equals(new BigDecimal("0.10")) || inputedMoney.equals(new BigDecimal("0.25"))
				|| inputedMoney.equals(new BigDecimal("0.50")) || inputedMoney.equals(new BigDecimal("2.00"))
				|| inputedMoney.equals(new BigDecimal("5.00")) || inputedMoney.equals(new BigDecimal("10.00"))) {
			result = Boolean.TRUE;
		}
		return result;

	}

}
