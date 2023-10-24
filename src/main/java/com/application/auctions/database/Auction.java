package com.application.auctions.database;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Auction {

    public Auction(){
        super();
    }

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private BigDecimal startPrice;

    private BigDecimal minPrice;

    //to Do Create Enum And create singular types of theme of auctions

    private String theme;
    // to Do  Create separate class and do max value of photos and use NOSQL database to safe them;

    private String photoLink;
    private Date dataOfferCreated;


    private Date dataOfferDue;
    private boolean isSold;

}
