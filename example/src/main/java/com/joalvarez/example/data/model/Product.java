package com.joalvarez.example.data.model;

import com.joalvarez.example.data.model.generals.MultitenantEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "products")
public class Product extends MultitenantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sku;
	private String name;
	private String description;
	private UUID userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public UUID getUserId() {
		return this.userId;
	}

	@Override
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
}
