package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    // Quan hệ nhiều-nhiều với bảng Position
    @ManyToMany(mappedBy = "roles")
    private Set<Position> positions;

    // Các getter và setter
}
