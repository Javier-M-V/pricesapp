package com.jmv.pricesapp.priceinfraestructure.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricesRepository extends CrudRepository<Prices, PricesId> {

    @Query(value = "SELECT * FROM PRICES p WHERE p.BRAND_ID = :brandId AND p.PRODUCT_ID= :productId AND :daterule BETWEEN p.START_DATE AND p.END_DATE ORDER BY p.PRIORITY DESC LIMIT 1", nativeQuery = true)
    Optional<Prices> findByBrandIdProductIdAndDateBetweenStartAndEnd(@Param("productId")Long productId, @Param("brandId")Long brandId, @Param("daterule") LocalDateTime daterule);

}
