package com.project.Projectwo.ETC;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {
		
		Optional<Member> _member = this.memberRepository.findByIdentity(identity);
		
		if(_member.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		Member member = _member.get();
		List<GrantedAuthority> authorities = new ArrayList<>();

		if("admin".equalsIgnoreCase(member.getRole())) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		} else if("teacher".equalsIgnoreCase(member.getRole())) {
			authorities.add(new SimpleGrantedAuthority(UserRole.TEACHER.getValue()));
		} else if("student".equalsIgnoreCase(member.getRole())){
			authorities.add(new SimpleGrantedAuthority(UserRole.STUDENT.getValue()));
		}
		return new User(member.getIdentity(), member.getPassword(), authorities);
	}

}
