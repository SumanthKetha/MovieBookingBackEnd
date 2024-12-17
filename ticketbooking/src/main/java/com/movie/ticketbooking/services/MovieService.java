package com.movie.ticketbooking.services;

import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepo repo;

    public List<Movie> getMoviesList() {
        return repo.findAll();
    }

    public Movie addMovie(Movie movie) {
        return repo.save(movie);
    }

    public Movie updateMovie(Movie updatedMovie, int id) {
        Optional<Movie> optionalMovie = repo.findById(id);

        if (optionalMovie.isPresent()) {
            updatedMovie.setId(id);
            return repo.save(updatedMovie);
        } else {
            throw new RuntimeException("Movie not found with this " + id);
        }

    }

    public void deleteMovie(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        repo.deleteById(id);
    }

    public Movie getMovieById(int movieId) {
        if(!repo.existsById(movieId)){
            throw new RuntimeException("Movie details not found with this "+movieId);
        }
        return repo.findById(movieId).get();
    }
}
