package world.inetum.realdolmen.realjobs.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import world.inetum.realdolmen.realjobs.BaseIntegrationTest;
import world.inetum.realdolmen.realjobs.InetumRealJobsApplication;
import world.inetum.realdolmen.realjobs.entities.Address;
import world.inetum.realdolmen.realjobs.entities.Company;
import world.inetum.realdolmen.realjobs.entities.Country;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.entities.enums.ContractType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(classes = InetumRealJobsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VacancyControllerIT extends BaseIntegrationTest {

    @Test
    public void findAllVacanciesWithFilter_GETVacancyNoMatch_NoContent() throws Exception {
        mockMvc.perform(
                        get("/api/vacancies")
                                .queryParam("functionTitle", "")
                                .queryParam("contractType", "")
                                .queryParam("industry", "")
                                .queryParam("country", "726")
                                .queryParam("requiredYearsOfExperience", "0")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void findAllVacanciesWithFilter_GETBelgium3YearsOfExpVacancy_Success() throws Exception {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("function3", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("function6", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        mockMvc.perform(
                        get("/api/vacancies")
                                .queryParam("functionTitle", "")
                                .queryParam("contractType", "")
                                .queryParam("industry", "")
                                .queryParam("country", String.valueOf(country1.getId()))
                                .queryParam("requiredYearsOfExperience", "3")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void findAllVacancies_GET_Success() throws Exception {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("function3", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("function6", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        mockMvc.perform(get("/api/vacancies/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(8));
    }

    @Test
    public void addVacancy_POSTEmptyVacancy_Fail() throws Exception {
        Vacancy vacancy = new Vacancy();

        mockMvc.perform(
                        post("/api/vacancies/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(vacancy))
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addVacancy_POSTValidVacancy_Success() throws Exception {
        Country country = persistCountry("Belgium");

        Address address = new Address();
        address.setStreetName("street");
        address.setHouseNumber("number");
        address.setBox(null);
        address.setCity("city");
        address.setPostalCode("postal");
        address.setCountry(country);

        Vacancy vacancy = new Vacancy();
        vacancy.setFunctionTitle("Vendor");
        vacancy.setContractType(ContractType.FULL_TIME);
        vacancy.setFunctionDescription("cash register and shelves");
        vacancy.setCompany(null);
        vacancy.setAddress(address);
        vacancy.setRequiredYearsOfExperience(5);
        vacancy.setRequirements(null);
        vacancy.setOffer("offer");
        vacancy.setRecruiter(null);

        mockMvc.perform(
                        post("/api/vacancies/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(vacancy))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.functionTitle").value("Vendor"));
    }


}
