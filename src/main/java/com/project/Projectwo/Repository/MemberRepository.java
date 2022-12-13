package com.project.Projectwo.Repository;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

=======
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
<<<<<<< HEAD
	Optional<Member> findByIdentity(String identity);
	Optional<Member> findByName(String name);
	Optional<Member> findByTel(String tel);
	Optional<Member> findByIdentityAndPassword(String identity, String password);
	
	List<Member> findByRole(String role);
=======

>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
}
