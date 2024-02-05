package com.jmv.pricesapp.priceinfraestructure;


import com.jmv.pricesapp.priceinfraestructure.persistence.Prices;
import com.jmv.pricesapp.priceinfraestructure.persistence.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.Optional;

@Service("pricesRetriever")
 public class PricesRetriever implements PricesService{

    private final PricesRepository pricesRepository;

    @Autowired
    public PricesRetriever(PricesRepository pricesRepository){
        this.pricesRepository = pricesRepository;
    }

    @Override
    public Optional<Prices> findByProductIdBrandIdAndDate(Long productId, Long brandId, LocalDateTime date) {

        return pricesRepository.findByBrandIdProductIdAndDateBetweenStartAndEnd(productId,brandId,date);
    }

}
