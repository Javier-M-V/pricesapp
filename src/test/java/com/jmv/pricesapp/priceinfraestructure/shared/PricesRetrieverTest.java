package com.jmv.pricesapp.priceinfraestructure.shared;

import com.jmv.pricesapp.priceinfraestructure.PricesRetriever;
import com.jmv.pricesapp.priceinfraestructure.persistence.Prices;
import com.jmv.pricesapp.priceinfraestructure.persistence.PricesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PricesRetrieverTest {


    @InjectMocks
    private PricesRetriever pricesRetriever;

    @Mock
    private PricesRepository pricesRepository;

    private static final LocalDateTime TEST_DATE = LocalDateTime.now();

    @Test
    void given_validData_findByProductIdBrandIdAndDate_returnResultOfQuery(){

        Optional<Prices> returnedOpPrices = Optional.of(new Prices());
        when(pricesRepository.findByBrandIdProductIdAndDateBetweenStartAndEnd(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(returnedOpPrices);

        pricesRetriever.findByProductIdBrandIdAndDate(1L, 1L,TEST_DATE);

        verify(pricesRepository, times(1)).findByBrandIdProductIdAndDateBetweenStartAndEnd(1L, 1L, TEST_DATE);
    }



}
