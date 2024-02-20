package com.joalvarez.example.config;

import com.joalvarez.example.interceptor.TenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Stream;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

	private final TenantInterceptor tenantInterceptor;

	public FilterConfig (TenantInterceptor tenantInterceptor) {
		this.tenantInterceptor = tenantInterceptor;
	}

	private static final List<String> EXCLUDE_PATH = Stream.of(
		"/v3/api-docs/**",
		"/swagger-ui/**",
		"/swagger-ui.html",
		"/swagger-resources/**",
		"/webjars/**",
		"/login"
	).toList();

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.tenantInterceptor)
			.excludePathPatterns(EXCLUDE_PATH);
	}
}