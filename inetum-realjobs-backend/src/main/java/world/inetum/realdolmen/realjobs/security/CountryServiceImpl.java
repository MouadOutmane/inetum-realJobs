package world.inetum.realdolmen.inetumrealJobs.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.inetumrealJobs.entities.Country;
import world.inetum.realdolmen.inetumrealJobs.repositories.CountryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public Country retrieveCountryById(Long id) {
        return countryRepository.getById(id);
    }

    @Override
    public List<Country> retrieveAllCountries(){
        return countryRepository.findAll();
    }
}
