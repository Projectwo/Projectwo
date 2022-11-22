package com.project.Projectwo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
