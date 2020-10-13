package com.boavista.snack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boavista.snack.dto.ProductResponseDTO;
import com.boavista.snack.entity.Product;
import com.boavista.snack.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductResponseDTO> getProductList(Long idMachine) {
		List<Product> listProduct = productRepository.findByIdMachine(idMachine);
		return populateProductDTO(listProduct);
	}

	private List<ProductResponseDTO> populateProductDTO(List<Product> listProduct) {
		List<ProductResponseDTO> listDTO = new ArrayList<>();
		for (Product product : listProduct) {
			ProductResponseDTO productDTO = new ProductResponseDTO();
			productDTO.setId(product.getId());
			productDTO.setDescription(product.getName());
			productDTO.setIdMachine(product.getIdMachine());
			productDTO.setQuantity(product.getQuantity());
			productDTO.setValue(product.getCharge());
			listDTO.add(productDTO);
		}
		return listDTO;
	}

	public void reset(Long idMachine) {
		List<Product>listProduct = productRepository.findByIdMachine(idMachine);
		for (Product product : listProduct) {
			product.setQuantity(new Short("0"));
			productRepository.save(product);
		}
	}
	
}
