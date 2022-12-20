package com.project.Projectwo.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByIdentity(String identity);
	Optional<Member> findByName(String name);
	Optional<Member> findByTel(String tel);
	Optional<Member> findByIdentityAndPassword(String identity, String password);
	
	List<Member> findByRole(String role);
}
