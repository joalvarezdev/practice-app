package com.joalvarez.example.data.dao;

import com.joalvarez.example.data.dao.generals.GenericDAO;
import com.joalvarez.example.data.model.Role;
import com.joalvarez.example.data.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleDAO extends GenericDAO<RoleRepository, Role, Long> {

	public RoleDAO(RoleRepository repository) {
		super(repository);
	}

	public Optional<Role> findByName(String name) {
		return this.repository.findByName(name);
	}
}
