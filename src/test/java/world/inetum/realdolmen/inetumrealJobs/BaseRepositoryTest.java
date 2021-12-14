package world.inetum.realdolmen.inetumrealJobs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.repositories.RoleRepository;
import world.inetum.realdolmen.inetumrealJobs.repositories.UserRepository;
import world.inetum.realdolmen.inetumrealJobs.repositories.VacancyRepository;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class BaseRepositoryTest {

    @Autowired
    protected RoleRepository roleRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected VacancyRepository vacancyRepository;

    @BeforeEach
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void cleanDatabase() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
        vacancyRepository.deleteAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected Vacancy persistVacancy(String function, String contractType, String description, String company, String industry, String country, String city, int postal, String street, int number, int experience, String offer) {
        return vacancyRepository.save(
                Vacancy.builder()
                        .functionTitle(function)
                        .functionDescription(description)
                        .contractType(contractType)
                        .companyName(company)
                        .industry(industry)
                        .country(country)
                        .city(city)
                        .postalCode(postal)
                        .streetName(street)
                        .nr(number)
                        .requiredYearsOfExperience(experience)
                        .offer(offer)
                        .build()
        );
    }

    protected Vacancy persistVacancy(String function, String contractType, String industry, String country, int experience) {
        return persistVacancy(function, contractType, "description", "company", industry, country, "city", 1000, "street", 1, experience, "offer");
    }

    protected static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
