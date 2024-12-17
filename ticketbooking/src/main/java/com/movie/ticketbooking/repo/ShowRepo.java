package com.movie.ticketbooking.repo;

import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepo extends JpaRepository<Show,Integer> {
    List<Show> findAllByMovieId_Id(Integer movieId);
}
