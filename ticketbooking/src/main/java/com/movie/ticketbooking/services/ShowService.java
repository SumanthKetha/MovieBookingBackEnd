package com.movie.ticketbooking.services;

import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.models.Show;
import com.movie.ticketbooking.repo.MovieRepo;
import com.movie.ticketbooking.repo.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepo repo;

    @Autowired
    private  MovieRepo movieRepo;

    public Show addShow(Show show) {
        Movie movie = movieRepo.findById(show.getIdMovie())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        System.out.println("Found movie"+movie);
        Show showtime = new Show();
        showtime.setMovieId(movie);  // Set the movie entity

        showtime.setShowDate(show.getShowDate());  // Assuming showDate is a string and needs to be converted
        System.out.println(show.getShowTime());
        showtime.setShowTime(show.getShowTime());  // Set show time

        // Save the showtime object
      return   repo.save(showtime);
    }

    public List<Show> getAllShowsList() {
        return repo.findAll();
    }

    public Show getShowById(int id) {
        if(!repo.existsById(id)){
            throw new RuntimeException("Show details not found with this "+id);
        }
        return repo.findById(id).get();
    }

    public Show updateShow(Show updatedShow, int id) {
        Optional<Show> optionalShow = repo.findById(id);

        if (optionalShow.isPresent()) {
            updatedShow.setId(id);
            return repo.save(updatedShow);
        } else {
            throw new RuntimeException("Show not found with this " + id);
        }

    }

    public void deleteShow(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Show not found with id: " + id);
        }
        repo.deleteById(id);
    }


    public List<Show> getAllShowsByMovieId(Integer movieId) {
        return repo.findAllByMovieId_Id(movieId);
    }
}
