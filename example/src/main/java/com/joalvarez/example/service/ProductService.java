package com.joalvarez.example.service;

import com.joalvarez.example.data.dao.ProductDAO;
import com.joalvarez.example.data.dto.ProductDTO;
import com.joalvarez.example.data.mapper.ProductMapper;
import com.joalvarez.example.exception.generals.NotImplementedException;
import com.joalvarez.example.service.generals.GenericService;
import com.joalvarez.example.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends GenericService<ProductDAO, ProductMapper> implements IProductService {

	public ProductService(ProductDAO productDAO, ProductMapper mapper) {
		super(productDAO, mapper);
	}

	@Override
	public List<ProductDTO> findAll() {
		return this.dao.findAll().stream()
			.map(this.mapper::toDTO)
			.toList();
	}

	@Override
	public ProductDTO findById(Long id) {
		return this.mapper.toDTO(this.dao.findById(id));
/*
		var product = this.dao.findById(id);

		if (Objects.isNull(product)) {

		}

		return this.mapper.toDTO(product);
*/
	}

	@Override
	public ProductDTO update(ProductDTO dto) {
		throw new NotImplementedException();
	}

	@Override
	public ProductDTO create(ProductDTO dto) {
		return this.mapper.toDTO(this.dao.save(this.mapper.fromDTO(dto)));
	}
}
