package com.project.Projectwo.Repository;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
<<<<<<< HEAD
	Optional<Room> findByName(String name);
=======

>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
}
