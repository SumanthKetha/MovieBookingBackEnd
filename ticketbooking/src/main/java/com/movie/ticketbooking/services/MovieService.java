package com.movie.ticketbooking.services;

import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.nio.file.StandardCopyOption;


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

    public Movie updateMovie(Movie movie, int id, MultipartFile file) throws IOException {
        Optional<Movie> optionalMovie = repo.findById(id);
        if (!optionalMovie.isPresent()) {
            throw new RuntimeException("Movie not found");
        }

        Movie existingMovie = optionalMovie.get();

        // Update movie fields
        existingMovie.setMovieName(movie.getMovieName());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setLanguage(movie.getLanguage());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setDuration(movie.getDuration());
        existingMovie.setRating(movie.getRating());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        existingMovie.setDirector(movie.getDirector());

        // Update image if a new file is provided
        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID() + "_" + originalFilename;
            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Set the image path in the entity
            existingMovie.setImagePath("/uploads/" + fileName);
        }

        return repo.save(existingMovie);
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
