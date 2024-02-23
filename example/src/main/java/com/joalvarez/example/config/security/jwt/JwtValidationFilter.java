package com.joalvarez.example.config.security.jwt;

import static com.joalvarez.example.config.security.jwt.JwtConstants.*;

import com.joalvarez.example.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.*;

public class JwtValidationFilter extends BasicAuthenticationFilter{

	public JwtValidationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		String header = request.getHeader(HEADER_AUTHORIZATION);

		String token = JwtConstants.getAccessToken(header);

		if (token.isEmpty()) {
			chain.doFilter(request, response);
			return;
		}

		try {
			Claims claims = JwtConstants.getClaims(token);

			String username = claims.getSubject();
			Object authorityClaims = claims.get("authorities");

			Collection<? extends GrantedAuthority> authorities = Arrays.asList(Utils.jsonToObjectMixing(
				authorityClaims.toString(), SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class, SimpleGrantedAuthority[].class));

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, authorities);

			SecurityContextHolder.getContext().setAuthentication(authToken);
			chain.doFilter(request, response);
		} catch (JwtException e) {
			Map<String, String> body = new HashMap<>();

			body.put("error", e.getMessage());
			body.put("message", "This token es invalidity");

			setReponse(response, body, HttpStatus.UNAUTHORIZED, MediaType.APPLICATION_JSON);
		}
	}
}