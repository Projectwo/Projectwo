package com.project.Projectwo.Repository;

<<<<<<< HEAD
=======
import java.util.Optional;

>>>>>>> main
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
<<<<<<< HEAD

=======
	Optional<Member> findByMemberId(String memberId);
>>>>>>> main
}
