package com.amentity_reservation_system.repos;

import com.amentity_reservation_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
