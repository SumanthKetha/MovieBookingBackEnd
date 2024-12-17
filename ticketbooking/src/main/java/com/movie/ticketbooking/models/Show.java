package com.movie.ticketbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "showtimes")
@Data
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="show_time")
    private String showTime;

    @Column(name = "date",nullable = false)
    private LocalDate showDate;

    @ManyToOne
    @JoinColumn(name = "movieId", referencedColumnName = "id", nullable = false)
    private Movie movieId;

    @OneToMany(mappedBy = "show",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Seat> seatsList;

    // Custom getter for movie ID
    public Integer getIdMovie() {
        return movieId != null ? movieId.getId() : null;
    }

    public Show() {
        showDate = LocalDate.now();  // Current date
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return showDate.format(formatter);
    }
}
