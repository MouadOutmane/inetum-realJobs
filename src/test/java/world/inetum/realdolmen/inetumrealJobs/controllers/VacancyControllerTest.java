//package world.inetum.realdolmen.inetumrealJobs.controllers;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import world.inetum.realdolmen.inetumrealJobs.InetumRealJobsApplication;
//import world.inetum.realdolmen.inetumrealJobs.entities.Address;
//import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
//import world.inetum.realdolmen.inetumrealJobs.entities.enums.ContractType;
//import world.inetum.realdolmen.inetumrealJobs.entities.enums.Country;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@AutoConfigureMockMvc
//@SpringBootTest(classes = InetumRealJobsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class VacancyControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void findAllVacanciesWithFilter_GETInexistantVacancy_Fail() throws Exception {
//
//        mockMvc.perform(get("/api/vacancies/?functionTitle=&contractType=&industry=&country=Chili&requiredYearsOfExperience=0"))
//                .andExpect(status().isNoContent())
//                .andReturn();
//    }
//
//    @Test
//    public void findAllVacanciesWithFilter_GETBelgium3YearsOfExpVacancy_Success() throws Exception {
//        mockMvc.perform(get("/api/vacancies/?functionTitle=&contractType=&industry=&country=Belgium&requiredYearsOfExperience=3"))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    public void findAllVacancies_GET_Success() throws Exception {
//        mockMvc.perform(get("/api/vacancies/all"))
//                .andExpect(status().isOk());
//    }
//
////    @Test
////    public void addVacancy_POSTEmptyVacancy_Fail() throws Exception {
////
////        Vacancy vacancy = new Vacancy();
////
////        mockMvc.perform(post("/api/vacancies/create")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(vacancy.toJson())
////                )
////                .andExpect(status().is4xxClientError());
////    }
//
////    @Test
////    public void addVacancy_POSTValidVacancy_Success() throws Exception {
////
////        Address address = new Address();
////        address.setCountry(Country.GERMANY);
////        address.setCity("Berlin");
////        address.setHouseNumber("11");
////        address.setPostalCode("10787");
////        address.setStreetName("Keithstraße");
////
////        Vacancy vacancy = new Vacancy();
////        vacancy.setFunctionTitle("Vendor");
////        vacancy.setContractType(ContractType.FULL_TIME);
////        vacancy.setOffer("2000/month");
////        vacancy.setRequiredYearsOfExperience(5);
////        vacancy.setFunctionDescription("cash register and shelves");
////        vacancy.setAddress(address);
////        vacancy.setCompanyName("Gucci");
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/api/vacancies/create")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(vacancy.toJson())
////                )
////                .andExpect(status().isOk());
////    }
//}
