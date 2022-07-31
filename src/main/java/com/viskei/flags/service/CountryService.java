package com.viskei.flags.service;

import com.viskei.flags.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountriesByName(List<String> countriesName);

    Country getCountryByName(String name);

    void downloadFlag(String countryName, String url, String path);
}
