package com.joalvarez.example.controller;

import com.joalvarez.example.data.dto.ProductDTO;
import com.joalvarez.example.service.ProductService;
import com.joalvarez.example.service.interfaces.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

	private final IProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(dto));
	}
}
