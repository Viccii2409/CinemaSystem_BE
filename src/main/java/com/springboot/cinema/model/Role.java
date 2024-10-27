package com.springboot.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @OneToMany(mappedBy = "role")
    private List<PositionRole> positionRoles;

    // Các getter và setter
}
