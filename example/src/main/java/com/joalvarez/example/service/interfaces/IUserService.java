package com.joalvarez.example.service.interfaces;

import com.joalvarez.example.service.generals.IBaseService;

public interface IUserService<DTO> extends IBaseService<DTO, Long>{

	DTO register(DTO dto);
}
