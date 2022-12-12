package com.project.Projectwo.ETC;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSessionListener implements HttpSessionListener {

	//세션 생성 이벤트 발생시 자동 실행 , 세션 유지 시간 10분 설정
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(600);
        log.info("sessionCreated/ 세션 생성");
        log.info("session time -> {}", httpSessionEvent.getSession().getMaxInactiveInterval());
        
    }
    
 // by 안준언, 세션 만료 이벤트 발생시 자동 실행, 세션 만료시 친구의 접속 상태를 false로 수정
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("sessionDestroyed -> {}", httpSessionEvent.getSession());
        
    }

}
