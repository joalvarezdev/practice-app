package com.joalvarez.example.config.security.jwt;

import com.joalvarez.example.constants.ErrorCode;
import com.joalvarez.example.exception.generals.GenericException;
import com.joalvarez.example.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Objects;

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

	public static Claims getTokenClaims(HttpServletRequest request) {

		var header = request.getHeader(HEADER_AUTHORIZATION);

		if (Objects.isNull(header) || !header.startsWith(PREFIX_TOKEN)) {
			throw new GenericException(
				HttpStatus.UNAUTHORIZED,
				ErrorCode.USER_UNIDENTIFIED
			);
		}

		var token = header.replace(PREFIX_TOKEN, "");

		if (token.isEmpty()) {
			throw new GenericException(
				HttpStatus.UNAUTHORIZED,
				ErrorCode.USER_UNIDENTIFIED
			);
		}

		return Jwts.parser()
			.verifyWith(SECRET_KEY)
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

	public static String getAccessToken(String header) {
		if (Objects.isNull(header) || !header.startsWith(PREFIX_TOKEN)) {
			return "";
		}

		return header.replace(PREFIX_TOKEN, "");
	}

	public static Claims getClaims(String token) {
		return Jwts.parser()
			.verifyWith(SECRET_KEY)
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}
}
