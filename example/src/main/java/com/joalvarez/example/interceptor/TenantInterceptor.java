package com.joalvarez.example.interceptor;

import com.joalvarez.example.config.security.jwt.JwtConstants;
import com.joalvarez.example.service.LocalStorage;
import com.joalvarez.example.shared.HasLogger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Component
public class TenantInterceptor implements HandlerInterceptor, HasLogger {

	private final LocalStorage localStorage;

	public TenantInterceptor(LocalStorage localStorage) {
		this.localStorage = localStorage;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		var claims = JwtConstants.getTokenClaims(request);

		this.localStorage.setUserDetails(UUID.fromString(claims.get("tenant").toString()));

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		this.localStorage.clear();
	}
}
