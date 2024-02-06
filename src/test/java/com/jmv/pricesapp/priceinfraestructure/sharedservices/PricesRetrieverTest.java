package com.jmv.pricesapp.priceinfraestructure.sharedservices;

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


@ExtendWith(MockitoExtension.class)
public class PricesRetrieverTest {


    public static final long PRODUCT_ID = 1L;
    public static final long BRAND_ID = 1L;
    @InjectMocks
    private PricesRetriever pricesRetriever;

    @Mock
    private PricesRepository pricesRepository;

    private static final LocalDateTime TEST_DATE = LocalDateTime.now();

    @Test
    void given_validData_findByProductIdBrandIdAndDate_returnResultOfQuery(){

        Optional<Prices> returnedOpPrices = Optional.of(new Prices());
        when(pricesRepository.findByBrandIdProductIdAndDateBetweenStartAndEnd(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(returnedOpPrices);

        pricesRetriever.findByProductIdBrandIdAndDate(PRODUCT_ID, BRAND_ID,TEST_DATE);

        verify(pricesRepository, times(1)).findByBrandIdProductIdAndDateBetweenStartAndEnd(1L, 1L, TEST_DATE);
    }

}
