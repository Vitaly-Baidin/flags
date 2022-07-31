package com.viskei.flags.client;

import com.viskei.flags.model.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "restCountries", url = "https://restcountries.com/v3.1/")
public interface CountryClient {

    @GetMapping(value = "/all")
    List<Country> findAll();

    @GetMapping(value = "/name/{country}")
    List<Country> getCountryByName(@PathVariable("country") String country);
}