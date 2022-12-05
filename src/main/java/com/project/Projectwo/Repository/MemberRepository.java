package com.project.Projectwo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByIdentity(String identity);
	Optional<Member> findByName(String name);
	
	List<Member> findByRole(String role);
	Optional<Member> findByIdentityAndPassword(String id, String pw);
}
