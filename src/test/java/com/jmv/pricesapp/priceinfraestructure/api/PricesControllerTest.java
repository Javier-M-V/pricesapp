package com.jmv.pricesapp.priceinfraestructure.api;


import com.jmv.pricesapp.domain.Price;
import com.jmv.pricesapp.priceinfraestructure.PricesService;

import com.jmv.pricesapp.priceinfraestructure.persistence.Prices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SuppressWarnings("ConstantConditions")
public class PricesControllerTest {


    @InjectMocks
    private PricesController pricesController;

    @Mock
    private  PricesService pricesService;

    private static final LocalDateTime DAY_TEST_START = LocalDateTime.of(1997, Month.AUGUST,29,10,0,0);

    private static final LocalDateTime DAY_TEST_END = LocalDateTime.of(1997, Month.AUGUST,29,11,0,0);

    static MockHttpServletRequest request = new MockHttpServletRequest();

    @BeforeEach
    void before(){
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }


    @Test
    void given_priceNotFoundOnService_controllerReturns_404(){

        when(pricesService.findByProductIdBrandIdAndDate(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(Optional.empty());

        var price = pricesController.prices(1L, 1L, LocalDateTime.now());

        assertEquals(404, price.getStatusCode().value());
        assertNull(price.getBody());
    }

    @Test
    void given_priceFoundOnService_controllerReturnsPriceDomain(){

        var pricesMock = new Prices();
        pricesMock.setPriceId(1L);
        pricesMock.setPrice(new BigDecimal("33.3"));
        pricesMock.setCurr("EUR");
        pricesMock.setBrandId(1L);
        pricesMock.setStartDate(DAY_TEST_START);
        pricesMock.setEndDate(DAY_TEST_END);
        when(pricesService.findByProductIdBrandIdAndDate(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(Optional.of(pricesMock));

        ResponseEntity<Price> price = pricesController.prices(1L, 1L, LocalDateTime.now());

        assertEquals(200, price.getStatusCode().value());
        assertInstanceOf(Price.class, price.getBody());
        assertEquals(DAY_TEST_START, price.getBody().getStartDate());
        assertEquals(DAY_TEST_END, price.getBody().getEndDate());
    }
}
