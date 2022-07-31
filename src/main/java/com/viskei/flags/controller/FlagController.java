package com.viskei.flags.controller;

import com.viskei.flags.model.Country;
import com.viskei.flags.payload.request.CountryRequest;
import com.viskei.flags.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class FlagController {

    private final CountryService countryService;

    @Autowired
    public FlagController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<?> downloadFlags(@Valid @RequestBody CountryRequest countryRequest) {

        List<Country> allCountriesByName = countryService.getAllCountriesByName(countryRequest.getCountries());
        String imageUrl;

        for (Country country : allCountriesByName) {
            if (countryRequest.getImageFormat().equalsIgnoreCase("svg")) {
                imageUrl = country.getFlags().getSvg();
            } else {
                imageUrl = country.getFlags().getPng();
            }

            countryService.downloadFlag(country.getName(), imageUrl, countryRequest.getPath());
        }

        return ResponseEntity.ok().body(allCountriesByName);
    }
}
