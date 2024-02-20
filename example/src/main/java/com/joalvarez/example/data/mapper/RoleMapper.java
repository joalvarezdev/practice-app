package com.joalvarez.example.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.example.data.dto.RoleDTO;
import com.joalvarez.example.data.mapper.generals.BaseMapper;
import com.joalvarez.example.data.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends BaseMapper<RoleDTO, Role> {

	public RoleMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
