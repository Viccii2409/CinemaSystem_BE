package com.springboot.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TypeRoom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeRoomID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "typeRoom")
    private List<Room> rooms;

    // Các getter và setter
}
