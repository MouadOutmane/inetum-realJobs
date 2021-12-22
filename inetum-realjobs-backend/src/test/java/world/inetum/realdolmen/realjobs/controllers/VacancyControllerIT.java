package world.inetum.realdolmen.realjobs.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
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

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void findAllVacanciesWithFilter_GETVacancyNoMatch_NoContent() throws Exception {
        mockMvc.perform(get("/api/vacancies/?functionTitle=&contractType=&industry=&country=7348&requiredYearsOfExperience=0"))
                .andExpect(status().isNoContent());
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
                        get("/api/vacancies/?functionTitle=&contractType=&industry=&country=&requiredYearsOfExperience=3")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void findAllVacancies_GET_Success() throws Exception{
        mockMvc.perform(get("/api/vacancies/all"))
                .andExpect(status().isOk());
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
        Address address = new Address();
        address.setStreetName("street");
        address.setHouseNumber("number");
        address.setBox(null);
        address.setCity("city");
        address.setPostalCode("postal");
        address.setCountry(null);

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
                .andExpect(status().isOk());
    }

    @Test
    public void findVacancy_Success() throws Exception {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");

        Company company1 = persistCompany("name1", "industry1", country1);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        Vacancy vacancy = persistVacancy("function3", ContractType.FLEXI, createAddress(country2), company1, 0);

        mockMvc.perform(
                        get("/api/vacancies/" + vacancy.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(vacancy.getId()))
                .andExpect(jsonPath("$.functionTitle").value("function3"))
                .andExpect(jsonPath("$.contractType").value(ContractType.FLEXI.name()))
                .andExpect(jsonPath("$.country").value("France"))
                .andExpect(jsonPath("$.companyCountry").value("Belgium"));
    }

    @Test
    public void findVacancy_NotFound() throws Exception {
        mockMvc.perform(
                        get(("/api/vacancies/5375"))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

}
