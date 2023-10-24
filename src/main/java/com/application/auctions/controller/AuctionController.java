package com.application.auctions.controller;

import com.application.auctions.database.Auction;
import com.application.auctions.service.AuctionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class AuctionController {


    @Autowired
    private AuctionService auctionService;

    @PostMapping()
    public Auction startAuction(@RequestBody Auction auction){
       return auctionService.createAction(auction);
    }

    @GetMapping(value = "/all")
    public List<Auction> getAllAuctions(){
        return auctionService.getAllAuctions();
    }

    @PutMapping("/{id}/{bid}")
    public Auction updateAuction(@PathVariable Long id ,@PathVariable BigDecimal bid){
      return auctionService.bidToAuction(id,bid);

    }

    @GetMapping("/pies")
    public  Auction getAuctionById(){

        Auction auction = Auction.builder()
                .id(1L)
                .title("Zajebiste Volvo S60 z rdza z 2018 po zmarlych dluznmiku ")
                .theme("cars")
                .startPrice(new BigDecimal(200))
                . minPrice(new BigDecimal(2500))
                .photoLink("link.com")
                .dataOfferCreated(new Date())
                .dataOfferDue(new Date())
                .description("ZajebisteAuto")
                .isSold(false)
                .build();


       return auction;

    }
}
