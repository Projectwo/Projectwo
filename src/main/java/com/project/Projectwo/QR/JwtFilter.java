package com.project.Projectwo.QR;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

//    public static final String AUTHORIZATION_HEADER = "Authorization";
//    public static final String BEARER_PREFIX = "Bearer ";
//    
//    private final JwtUtil jwtUtil;
//
//    /**
//     * 토큰 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
//     */
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
//    		throws IOException, ServletException {
//
//        // Request Header에서 토큰 추출
//        String jwt = resolveToken(request);
//
//        // Token 유효성 검사
//        if (StringUtils.hasText(jwt) && jwtUtil.isValidToken(jwt)) {
//            // 토큰으로 인증 정보를 추출
//            Authentication authentication = jwtUtil.getAuthentication(jwt);
//            
//            // SecurityContext에 저장(?)
//            //SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    /**
//     * Request Header에서 토큰 추출
//     */
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
    
}
