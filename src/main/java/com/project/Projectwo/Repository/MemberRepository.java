package com.project.Projectwo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByIdentity(String identity);
	Optional<Member> findByMemberIdAndMemberPw(String id, String pw);

}
