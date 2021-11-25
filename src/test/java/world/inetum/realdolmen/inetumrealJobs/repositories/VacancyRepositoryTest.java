package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ActiveProfiles;
import world.inetum.realdolmen.inetumrealJobs.jpa.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.services.VacancyService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.data.jpa.domain.Specification.where;
import static world.inetum.realdolmen.inetumrealJobs.repositories.VacancySpecification.*;


@ActiveProfiles("test")
@SpringBootTest
public class VacancyRepositoryTest {

    @Autowired
    VacancyRepository vacancyRepository;

    @Test
    void FindFullTimeVacanciesInItalyWith3MinYearsOfExperience(){
        String contractType= "Full-Time";
        String country = "italy";
        Integer requiredYearsOfExperience = 3;

        List<Vacancy> results = vacancyRepository.findAll(where(withContractType(contractType))
                .and(withCountry(country))
                .and(withRequiredYearsOfExperience(requiredYearsOfExperience)));


        for (Vacancy v: results) { // todo delete print
            System.out.println(v.getCity());
        }
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());

    }
}
