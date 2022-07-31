package com.viskei.flags.client;

import com.viskei.flags.model.Country;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryClientTest {

    private final CountryClient countryClient;

    @Autowired
    public CountryClientTest(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    @Test
    public void givenCountryClient_getAllCountries() {
        List<Country> countries = countryClient.findAll();

        assertTrue(countries.size() != 0);
    }

    @Test
    public void givenCountryClient_getUSA_Country() {
        List<Country> country = countryClient.getCountryByName("USA");

        assertTrue(country.size() != 0 &&
                country.get(0).getName().equalsIgnoreCase("United States of America"));
    }

    @Test
    public void givenCountryClient_getErrorCountry() {
        Throwable thrown = assertThrows(FeignException.NotFound.class,
                () -> countryClient.getCountryByName("USAAAAADDD"));

        assertNotNull(thrown.getMessage());
    }
}