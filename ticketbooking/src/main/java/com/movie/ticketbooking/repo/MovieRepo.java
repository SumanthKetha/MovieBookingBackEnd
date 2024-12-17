package com.movie.ticketbooking.repo;

import com.movie.ticketbooking.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie,Integer> {
}
