package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Status" , nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "TheaterID", nullable = false)
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "TypeRoomID", nullable = false)
    private TypeRoom typeRoom;
    // Quan hệ với bảng Showtime
    @OneToMany(mappedBy = "room")
    private Set<Showtime> showtimes;

    @OneToMany(mappedBy = "room")
    private Set<Seat> seats;

    // Các getter và setter
}

