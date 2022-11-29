package com.project.Projectwo.QR;

import java.security.Key;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

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
	
	Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
    //access token 유효시간
    private final long accessTokenValidTime = 2 * 60 * 60 * 1000L;

    //refresh token 유효시간
    private final long refreshTokenValidTime = 2 * 7 * 24 * 60 * 60 * 1000L;

	
	//토큰 생성
	public String createJwt(String id) {

		return Jwts.builder()
				   .setHeaderParam("type", "jwt")
				   .claim("id", id)
				   .setExpiration(new Date(System.currentTimeMillis() + 1*(1000*60*60*24)))
				   .signWith(secretKey, SignatureAlgorithm.HS256)
				   .compact();

	}
	
    //헤더에서 JWT 추출
	//어..이거 아닌 거 같아
    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }
    
    
    //JWT에서 값 추출
    public String getMemberId(String token) throws Exception{
    	
        // 헤더에서 JWT 추출
        if(token == null || token.length() == 0){
            throw new Exception();
        }

        // JWT 파싱
        Jws<Claims> claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
        } catch (Exception ignored) {
            throw new Exception();
        }

        // id 추출
        String id = claims.getBody().getId();
        
        log.info("id: " + id);
        
        return id;
    }
    
    

//	//토큰 유효성 검증
//    public boolean isValidToken(String token) {
//        try {
//            Claims claims = getClaimsFormToken(token);
//            return !claims.getExpiration().before(new Date());
//        } catch (JwtException | NullPointerException exception) {
//            return false;
//        }
//    }
//    
//    
//    
//    // TODO: 헤더에서 JWT 추출
//    public String getJwt(){
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//        return request.getHeader();
//    } 
    
    
    
    
    
    
    

//    //토큰에서 Claim 추출
//    private Claims getClaimsFormToken(String token) {
//    	String secretKey = Base64.getEncoder().encodeToString(key.getBytes());
//    	
//        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token).getBody();
//    }
//
//    //토큰에서 인증 subject 추출
//    private String getSubject(String token) {
//        return getClaimsFormToken(token).getSubject();
//    }
//


}
