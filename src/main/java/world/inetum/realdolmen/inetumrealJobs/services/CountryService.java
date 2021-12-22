package world.inetum.realdolmen.inetumrealJobs.services;

import world.inetum.realdolmen.inetumrealJobs.entities.Country;

import java.util.List;

public interface CountryService {

    Country retrieveCountryById(Long id);
    List<Country> retrieveAllCountries();
}
