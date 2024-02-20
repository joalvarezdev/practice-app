package com.joalvarez.example.config.security.dto;

import com.joalvarez.example.data.dto.generals.BaseDTO;

import java.util.List;

public class EndPointSecurityDTO implements BaseDTO {

	private String path;
	private List<String> authorities;

	public String getPath() {
		return path;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
}
