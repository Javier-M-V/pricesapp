package com.jmv.pricesapp.priceinfraestructure.sharedservices;

import com.jmv.pricesapp.priceinfraestructure.persistence.Prices;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricesService {

    Optional<Prices> findByProductIdBrandIdAndDate(Long productId, Long brandId, LocalDateTime date);
}
