package com.boavista.snack.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boavista.snack.entity.Product;
import com.boavista.snack.repository.ProductRepository;

@RestController
@RequestMapping("client")
@Service
public class SnackClientControler {

	@Autowired ProductRepository productRepository;
	
	
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public Optional<Product> getSnacks() {
		System.out.println(productRepository.findById(1L));
		return productRepository.findById(1L);
		//return "<html><body><h1>API IS ON!</h1><img src='https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Stevie_Wonder_1994.jpg/220px-Stevie_Wonder_1994.jpg'></body></html>";	
	}
	
}
