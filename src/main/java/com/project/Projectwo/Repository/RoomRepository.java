package com.project.Projectwo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	Optional<Room> findByName(String name);

}
