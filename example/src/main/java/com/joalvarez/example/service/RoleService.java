package com.joalvarez.example.service;

import com.joalvarez.example.data.dao.RoleDAO;
import com.joalvarez.example.data.dto.RoleDTO;
import com.joalvarez.example.data.mapper.RoleMapper;
import com.joalvarez.example.exception.generals.NotImplementedException;
import com.joalvarez.example.service.generals.GenericService;
import com.joalvarez.example.service.interfaces.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService extends GenericService<RoleDAO, RoleMapper> implements IRoleService<RoleDTO> {

	public RoleService(RoleDAO roleDAO, RoleMapper mapper) {
		super(roleDAO, mapper);
	}

	@Override
	public List<RoleDTO> findAll() {
		throw new NotImplementedException();
	}

	@Override
	public RoleDTO findById(Long id) {
		throw new NotImplementedException();
	}

	@Override
	public RoleDTO update(RoleDTO roleDTO) {
		throw new NotImplementedException();
	}

	@Override
	public RoleDTO create(RoleDTO roleDTO) {
		throw new NotImplementedException();
	}

	@Override
	public Optional<RoleDTO> findByName(String name) {
		return this.dao.findByName(name)
			.map(this.mapper::toDTO);
	}
}
