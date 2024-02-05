package com.jmv.pricesapp.priceinfraestructure.api;

import com.jmv.pricesapp.priceinfraestructure.PricesService;
import com.jmv.pricesapp.priceinfraestructure.persistence.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jmv.pricesapp.domain.Price;



import java.time.LocalDateTime;
import java.util.Optional;


@RequestMapping("/")
@RestController
public class PricesController {

    private final PricesService pricesRetrieverService;

    @Autowired
    public PricesController(PricesService pricesRetrieverService){
        this.pricesRetrieverService = pricesRetrieverService;
    }

    @GetMapping(value="prices")
    public ResponseEntity<Price> prices(@RequestParam Long productId,
                                         @RequestParam Long brandId,
                                         @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                             LocalDateTime dateRule){

        Optional<Prices> prices = pricesRetrieverService.findByProductIdBrandIdAndDate(productId, brandId, dateRule);

        return prices.map(this::map).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private ResponseEntity<Price> map(Prices prices) {

        Price priceDomain = new Price();
        priceDomain.setPriceId(prices.getPriceId());
        priceDomain.setProductId(prices.getProductId());
        priceDomain.setBrandId(prices.getBrandId());
        priceDomain.setStartDate(prices.getStartDate());
        priceDomain.setEndDate(prices.getEndDate());
        priceDomain.setPrice(prices.getPrice());
        return ResponseEntity.ok().body(priceDomain);
    }

}
