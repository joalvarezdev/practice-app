package com.joalvarez.example.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.example.data.dto.ProductDTO;
import com.joalvarez.example.data.mapper.generals.BaseMapper;
import com.joalvarez.example.data.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<ProductDTO, Product> {

	public ProductMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
