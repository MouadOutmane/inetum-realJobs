package world.inetum.realdolmen.inetumrealJobs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.inetum.realdolmen.inetumrealJobs.entities.Country;
import world.inetum.realdolmen.inetumrealJobs.services.CountryServiceImpl;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryServiceImpl countryService;

    @Autowired
    public CountryController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    ResponseEntity<List<Country>> findAllVacancies() {
        System.out.println("get all countries");
        List<Country> results = countryService.retrieveAllCountries();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
