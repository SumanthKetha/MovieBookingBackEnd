package com.movie.ticketbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seat_list")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    @Column(name = "seat_type")
    private String seatType;


    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "show_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore  // Prevent infinite nesting in the JSON response
    private Show show;

}
