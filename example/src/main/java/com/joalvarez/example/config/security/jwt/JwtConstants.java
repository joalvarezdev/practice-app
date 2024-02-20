package com.joalvarez.example.config.security.jwt;

import com.joalvarez.example.utils.Utils;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.crypto.SecretKey;
import java.io.IOException;

public class JwtConstants {

	public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
	public static final String PREFIX_TOKEN = "Bearer ";
	public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final long EXPIRATION_TIME = 43_200_000;

	public static void setReponse(HttpServletResponse response, Object body, HttpStatus status, MediaType contentType) throws IOException {
		response.getWriter().write(Utils.objectToJson(body));
		response.setStatus(status.value());
		response.setContentType(contentType.toString());
	}
}
