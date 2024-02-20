package com.joalvarez.example.config.security;

import com.joalvarez.example.config.security.constants.SecurityProperties;
import com.joalvarez.example.config.security.jwt.JwtAuthenticationFilter;
import com.joalvarez.example.config.security.jwt.JwtValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

	private final SecurityProperties properties;
	private final AuthenticationConfiguration authenticationConfiguration;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return this.authenticationConfiguration.getAuthenticationManager();
	}

	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
						  SecurityProperties properties) {
		this.authenticationConfiguration = authenticationConfiguration;
		this.properties = properties;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {

				auth.requestMatchers("/users/register").hasRole("ADMIN");

				this.properties.getEndpoints().forEach(endpoint -> {
					auth.requestMatchers(endpoint.getPath()).hasAnyRole(endpoint.getAuthorities().toArray(String[]::new));
				});

				auth.requestMatchers(this.properties.getExcludedPaths()).permitAll()
					.anyRequest().authenticated();
			})
			.addFilter(new JwtAuthenticationFilter(this.authenticationManager()))
			.addFilter(new JwtValidationFilter(this.authenticationManager()))
			.csrf(AbstractHttpConfigurer::disable)
			.cors(cors -> cors.configurationSource(this.corsConfigurationSource()))
			.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOrigins(this.properties.getOrigins());
		config.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
		config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(new CorsFilter(this.corsConfigurationSource()));

		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return filter;
	}
}
