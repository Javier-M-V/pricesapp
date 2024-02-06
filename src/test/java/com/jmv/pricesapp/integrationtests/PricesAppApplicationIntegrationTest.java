package com.jmv.pricesapp.integrationtests;

import com.jmv.pricesapp.PricesAppApplication;
import com.jmv.pricesapp.domain.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PricesAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("Tests de integración")
@SuppressWarnings("ConstantConditions")
public class PricesAppApplicationIntegrationTest {

    private static final String BASE_URL= "http://localhost:8080/prices?";

    private static final RestTemplate restTemplate = new RestTemplate();

    private static String params;

    @Test
    void given_2020_06_14T10_00_000_api_returnsPriceId_1(){

        params = "productId=35455&brandId=1&dateRule=2020-06-14T10:00:00.000";

        ResponseEntity<Price> price = restTemplate.getForEntity(BASE_URL+params, Price.class);

        assertEquals(1,price.getBody().getPriceId());
        assertEquals(HttpStatus.OK, price.getStatusCode());
    }

    @Test
    void given_2020_06_14T16_00_000_api_returnsPriceId_2(){

        params = "productId=35455&brandId=1&dateRule=2020-06-14T16:00:00.000";

        ResponseEntity<Price> price = restTemplate.getForEntity(BASE_URL+params, Price.class);

        assertEquals(2,price.getBody().getPriceId());
        assertEquals(HttpStatus.OK, price.getStatusCode());
    }

    @Test
    void given_2020_06_14T21_00_000_api_returnsPriceId_1(){

        params = "productId=35455&brandId=1&dateRule=2020-06-14T21:00:00.000";

        ResponseEntity<Price> price = restTemplate.getForEntity(BASE_URL+params, Price.class);

        assertEquals(1,price.getBody().getPriceId());
        assertEquals(HttpStatus.OK, price.getStatusCode());
    }
    @Test
    void given_2020_06_15T10_00_000_api_returnsPriceId_3(){

        params = "productId=35455&brandId=1&dateRule=2020-06-15T10:00:00.000";

        ResponseEntity<Price> price = restTemplate.getForEntity(BASE_URL+params, Price.class);

        assertEquals(3,price.getBody().getPriceId());
        assertEquals(HttpStatus.OK, price.getStatusCode());
    }

    @Test
    void given_2020_06_15T10_00_000_api_returnsPriceId_4(){

        params = "productId=35455&brandId=1&dateRule=2020-06-16T21:00:00.000";

        ResponseEntity<Price> price = restTemplate.getForEntity(BASE_URL+params, Price.class);

        assertEquals(4,price.getBody().getPriceId());
        assertEquals(HttpStatus.OK, price.getStatusCode());
    }

    @Test
    void given_2021_06_14T01_30_00_000_api_returns404(){

        params = "productId=35455&brandId=1&dateRule=2021-06-14T01:30:00.000";

        try{
            restTemplate.getForEntity(BASE_URL+params, Price.class);
            fail();
        }catch (HttpClientErrorException ex){
            assertEquals(404, ex.getStatusCode().value());
        }
    }

    @Test
    void given_2021_06_14T01_30_00_000_api_returnsBadRequest(){

        params = "productId=35455&brandId=BAD_DATA&dateRule=2021-06-14T01:30:00.000";

        try{
            restTemplate.getForEntity(BASE_URL+params, Price.class);
            fail();
        }catch (HttpClientErrorException ex){
            assertEquals(400, ex.getStatusCode().value());
        }
    }

}
