package com.boavista.snack.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boavista.snack.dto.BuyProductRequestDTO;
import com.boavista.snack.dto.BuyProductResponseDTO;
import com.boavista.snack.dto.CancelPaymentResponseDTO;
import com.boavista.snack.dto.InputMoneyRequestDTO;
import com.boavista.snack.dto.InputMoneyResultDTO;
import com.boavista.snack.dto.MoneyResponseDTO;
import com.boavista.snack.dto.ProductResponseDTO;
import com.boavista.snack.service.PaymentService;
import com.boavista.snack.service.ProductService;

@RestController
@RequestMapping("admin")
public class SnackAdminControler {

	@Autowired
	PaymentService paymentService;
	@Autowired
	ProductService productService;



	@RequestMapping(method = RequestMethod.GET, path = "/snacks/{idMachine}")
	public @ResponseBody List<ProductResponseDTO> productsIntoMachine(@PathVariable Long idMachine) {
		return productService.getProductList(idMachine);
	}
	@RequestMapping(method = RequestMethod.GET, path = "/money/{idMachine}")
	public @ResponseBody List<MoneyResponseDTO> moneyIntoMachine(@PathVariable Long idMachine) {
		return paymentService.getMoneyList(idMachine);
	}


}
