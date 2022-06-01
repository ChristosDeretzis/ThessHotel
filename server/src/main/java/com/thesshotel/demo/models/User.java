package com.thesshotel.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name="email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name="password", nullable = false, length = 64)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "str_address")
    private String strAddress;

    @Column(name = "str_no")
    private String strNumber;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "isHotelOwner")
    private Boolean isHotelOwner;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-reservations")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-reviews")
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_owner",
            joinColumns =
                    { @JoinColumn(name = "owner_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "hotel_id", referencedColumnName = "id") })
    private Hotel hotel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
