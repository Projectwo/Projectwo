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

}
