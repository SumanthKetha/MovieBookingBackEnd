package com.movie.ticketbooking.repo;

import com.movie.ticketbooking.models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepo extends JpaRepository<Screen, Integer> {
    List<Screen> findByTheatreId(Integer theatreId);
    // Additional methods can be added here as required
}