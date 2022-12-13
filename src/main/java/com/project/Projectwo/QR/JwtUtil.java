package com.project.Projectwo.QR;

import java.security.Key;
import java.sql.Date;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
	
	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	
	private static final String secretKey = "236979CB6F1AD6B6A6184A31E6BE37DB3818CC36871E26235DD67DCFE4041492";
    //access token 유효시간
    private final long accessTokenValidTime = 2 * 60 * 60 * 1000L;

    //refresh token 유효시간
    private final long refreshTokenValidTime = 2 * 7 * 24 * 60 * 60 * 1000L;
	
	
	//토큰 생성
	public String createJwt(String id) {
		
		return Jwts.builder()
				   .setHeaderParam("type", "jwt")
				   .claim("id", id)
				   .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidTime))
				   .signWith(key, SignatureAlgorithm.HS256)
				   .compact();

	}

	
	
	//TODO: 토큰 복호화	
	//getSubject
    public static String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    
    //getClaims
    public static Claims getTokenData(String token) {
    	Claims claims = Jwts.parser()
    			.setSigningKey(secretKey.getBytes())
    			.parseClaimsJws(token).getBody();
    	return claims;
    }


}
