package world.inetum.realdolmen.inetumrealJobs.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VacancyEntityTest {

    @Test
    public void simpleGetterAndSetterTest() {
        Vacancy vacancy = new Vacancy();

        vacancy.setCompanyName("inetum");
        vacancy.setContractType("Full-Time");
        vacancy.setFunctionTitle("Junior Consultant");
        vacancy.setIndustry("IT");
        vacancy.setCity("Brussels");
        vacancy.setBox("B");
        vacancy.setStreetName("avenue de la liberte");
        vacancy.setNr(11);
        vacancy.setRequiredExperienceSkillsEducation("nothing");
        vacancy.setFunctionDescription("function");
        vacancy.setRequiredYearsOfExperience(10);
        vacancy.setPostalCode(1000);
        vacancy.setCountry("Belgium");
        vacancy.setPostingDate("10/10/2021");
        vacancy.setOffer("offer");

        assertNotNull(vacancy.getCompanyName());
        assertNotNull(vacancy.getContractType());
        assertNotNull(vacancy.getFunctionTitle());
        assertNotNull(vacancy.getIndustry());
        assertNotNull(vacancy.getCity());
        assertNotNull(vacancy.getBox());
        assertNotNull(vacancy.getStreetName());
        assertNotNull(vacancy.getNr());
        assertNotNull(vacancy.getRequiredExperienceSkillsEducation());
        assertNotNull(vacancy.getRequiredYearsOfExperience());
        assertNotNull(vacancy.getFunctionDescription());
        assertNotNull(vacancy.getPostalCode());
        assertNotNull(vacancy.getCountry());
        assertNotNull(vacancy.getPostingDate());
        assertNotNull(vacancy.getOffer());

    }


}
