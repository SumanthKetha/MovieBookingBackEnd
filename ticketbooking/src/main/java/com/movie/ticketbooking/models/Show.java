package com.movie.ticketbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "showtimes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;


        private LocalTime showTime;
        private LocalDate showDate;

        @ManyToOne
        @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
        @JsonIgnoreProperties("showTimes")
        private Movie movie;

        @ManyToOne
        @JoinColumn(name = "screen_id", referencedColumnName = "id", nullable = false)
        @JsonIgnoreProperties("shows")
        private Screen screen;
    }


