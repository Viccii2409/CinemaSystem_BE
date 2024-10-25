package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Theater")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TheaterID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Image")
    private String image;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "AddressDetail")
    private String addressDetail;

    @Column(name = "Ward")
    private String ward;

    @Column(name = "District")
    private String district;

    @Column(name = "City")
    private String city;

    // Quan hệ với bảng Room
    @OneToMany(mappedBy = "theater")
    private Set<Room> rooms;

    @OneToMany(mappedBy = "theater")
    private Set<User> users;

    // Các getter và setter
}
