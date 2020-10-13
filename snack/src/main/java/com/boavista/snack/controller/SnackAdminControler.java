package com.boavista.snack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boavista.snack.dto.LoadRequestDTO;
import com.boavista.snack.dto.LoadResponseDTO;
import com.boavista.snack.dto.MoneyResponseDTO;
import com.boavista.snack.dto.ProductResponseDTO;
import com.boavista.snack.dto.ResetRequestMachineDTO;
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

	@RequestMapping(method = RequestMethod.POST, path = "/reset")
	public void resetMachine(@RequestBody ResetRequestMachineDTO resetRequestMachine) {
		if(resetRequestMachine.getMoney()) {
			paymentService.reset(resetRequestMachine.getIdMachine());
		}
		if(resetRequestMachine.getProduct()) {
			productService.reset(resetRequestMachine.getIdMachine());
		}

	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/loadproduct")
	public LoadResponseDTO loadProduct(@RequestBody LoadRequestDTO productDTO) {
		return productService.loadProduct(productDTO);
	}
	@RequestMapping(method = RequestMethod.POST, path = "/loadmoney")
	public LoadResponseDTO loadMoney(@RequestBody LoadRequestDTO requestDTO) {
		return paymentService.loadMoney(requestDTO);
	}

}
