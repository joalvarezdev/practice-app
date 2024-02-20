package com.joalvarez.example.service.interfaces;

import com.joalvarez.example.service.generals.IBaseService;

import java.util.Optional;

public interface IRoleService<DTO> extends IBaseService<DTO, Long>{

	Optional<DTO> findByName(String name);
}
