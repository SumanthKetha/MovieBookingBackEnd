package com.movie.ticketbooking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie.ticketbooking.enums.Category;
import com.movie.ticketbooking.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String movieName;

    private Integer duration;

    private String description;

    private Double rating;

    private Date releaseDate;

    private String director;

    @Enumerated(value = EnumType.STRING)
    private Category genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movieId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Show> showTimes;

    public Movie(Integer id) {
        this.id = id;
    }
}
