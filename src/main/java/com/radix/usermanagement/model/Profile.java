package com.radix.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String userId;

    @Column(length = 45)
    private String firstName;

    @Column(length = 45)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @Column(length = 45)
    private String phone;

    @OneToOne
    @JoinColumn(name = "cardNumber")
    private CreditCard creditCard;

    @Column(length = 255)
    private String photo;
}