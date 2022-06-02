package com.thesshotel.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "str_address")
    private String strAddress;

    @Column(name = "str_number")
    private String strNumber;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "stars")
    private int stars;

    @OneToMany(mappedBy = "hotel")
    @JsonBackReference(value = "hotel-rooms")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "hotel")
    @JsonBackReference(value = "hotel-reviews")
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "hotel")
    private User owner;
}
