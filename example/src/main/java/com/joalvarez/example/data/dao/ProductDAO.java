package com.joalvarez.example.data.dao;

import com.joalvarez.example.data.dao.generals.MultitenantDAO;
import com.joalvarez.example.data.model.Product;
import com.joalvarez.example.data.repository.ProductRepository;
import com.joalvarez.example.service.LocalStorage;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO extends MultitenantDAO<ProductRepository, Product, Long> {


	public ProductDAO(ProductRepository repository, LocalStorage localStorage) {
		super(repository, localStorage);
	}

	public Product findBySKU(String sku) {

		return this.repository.findBySkuAndUserId(sku, this.getCurrentUserId());
	}
}
