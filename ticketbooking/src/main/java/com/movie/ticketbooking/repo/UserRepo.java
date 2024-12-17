package com.movie.ticketbooking.repo;

import com.movie.ticketbooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
   User findByEmail(String email);
}
