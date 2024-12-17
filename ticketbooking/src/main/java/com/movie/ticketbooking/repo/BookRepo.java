package com.movie.ticketbooking.repo;

import com.movie.ticketbooking.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Booking, Integer> {
    List<Booking> findByUserId(Integer userId);
}
