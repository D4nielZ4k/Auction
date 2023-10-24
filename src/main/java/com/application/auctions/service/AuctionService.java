package com.application.auctions.service;

import com.application.auctions.database.Auction;
import com.application.auctions.database.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;


    public Auction bidToAuction(final Long auctionId, BigDecimal bid) {
        //sprawdzenie czy cena jest wieksza od zakladanej
        Auction auction = auctionRepository.getById(auctionId);

        if(auction.getStartPrice().compareTo(bid)>=0 ){
            System.out.println("cena Psie za mala");
            //TO DO stworzenie odpowiedniego Requsta Oraz DTO do tego
           return Auction.builder()
                   .startPrice(auction.getStartPrice())
                   .build();
        }
        auction.setStartPrice(bid);
        auction.setDataOfferDue(longerBidingTime(auction.getDataOfferDue()));

        return auctionRepository.save(auction);
        // i zaaktualizowanie tej daty w bazie danych
    }

    public Auction createAction(Auction auction) {

        //14 days of Auction
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(auction.getDataOfferCreated());
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        Date dateOfFinalAuction = calendar.getTime();
        auction.setDataOfferDue(dateOfFinalAuction);


        //to DO ---------> validation
        return auctionRepository.save(auction);
    }

    public List<Auction> getAllAuctions() {
        List<Auction> auctions = (List<Auction>) auctionRepository.findAll();
        return auctions;
    }


    public Date longerBidingTime (Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 5);

        return calendar.getTime();
    }

}
