package com.viskei.flags.service.impl;

import com.viskei.flags.client.CountryClient;
import com.viskei.flags.model.Country;
import com.viskei.flags.service.CountryService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryClient countryClient;

    @Autowired
    public CountryServiceImpl(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    public List<Country> getAllCountriesByName(List<String> countriesName) {
        List<Country> countries = new ArrayList<>();

        for (String countryName : countriesName) {
            Country country = getCountryByName(countryName);
            if (country != null) countries.add(country);
        }
        return countries;
    }

    @Override
    public Country getCountryByName(String name) {
        Country country = null;

        try {
            country = countryClient.getCountryByName(name).get(0);
        } catch (FeignException.NotFound e) {
            log.error(e.getMessage());
        }

        return country;
    }

    @Override
    public void downloadFlag(String countryName, String url, String path) {

        Thread thread = new Thread(() -> {
            Country country = getCountryByName(countryName);
            if (country != null) {
                try {
                    URL imageUrl = new URL(url);
                    File destination = new File(createPath(countryName, url, path));

                    FileUtils.copyURLToFile(imageUrl, destination);
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
            }
        });
        thread.start();
    }

    private String createPath(String countryName, String url, String path) {
        return path + "/" +
                countryName.replace(' ', '_').toLowerCase() +
                "." + FilenameUtils.getExtension(url);
    }
}
