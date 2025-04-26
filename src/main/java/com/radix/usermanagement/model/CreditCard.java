package com.radix.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "creditcards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {

    @Id
    @Column(name = "card_number", length = 20)
    private String cardNumber;

    @Column(name = "expiry_date")
    private Date expiryDate;

    private Integer cvv;
    private String provider;

    @Column(name = "name_on_card")
    private String nameOnCard;
}