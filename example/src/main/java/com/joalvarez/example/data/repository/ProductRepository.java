package com.joalvarez.example.data.repository;

import com.joalvarez.example.data.model.Product;
import com.joalvarez.example.data.repository.generals.MultitenantRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ProductRepository extends MultitenantRepository<Product, Long> {

	Product findBySkuAndUserId(String sku, UUID currentUserId);
}
