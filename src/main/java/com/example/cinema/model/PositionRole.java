package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PositionRole")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PositionRoleID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PositionID",nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "RoleID", nullable = false)
    private Role role;
}
