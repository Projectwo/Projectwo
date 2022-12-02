package com.project.Projectwo.ETC;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

   private final MemberRepository memberRepository;

   // by 안준언, 로그인한 세션으로 멤버의 접속 상태를 true로 변경
   @Override
   public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response,
         Authentication authentication) throws IOException, ServletException {

      System.out.println("authentication:: " + authentication.getName());
      httpServletRequest.getSession().setAttribute("identity", authentication.getName());
      System.out.println(httpServletRequest.getSession().getAttribute("identity"));
      Optional<Member> oMember = memberRepository.findByIdentity(httpServletRequest.getSession().getAttribute("identity").toString());
      Member member = oMember.get();
      memberRepository.save(member);

      response.sendRedirect("/main");

   }
}
