package world.inetum.realdolmen.realjobs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import world.inetum.realdolmen.realjobs.BaseIntegrationTest;
import world.inetum.realdolmen.realjobs.InetumRealJobsApplication;
import world.inetum.realdolmen.realjobs.entities.Resume;
import world.inetum.realdolmen.realjobs.entities.enums.ResumeStatus;
import world.inetum.realdolmen.realjobs.entities.enums.SkillLevel;
import world.inetum.realdolmen.realjobs.payload.dtos.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = InetumRealJobsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
class ResumeControllerIT extends BaseIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    private final WebApplicationContext context;
    private final EntityManager em;

    @Autowired
    ResumeControllerIT(WebApplicationContext context, EntityManager em) {
        this.context = context;
        this.em = em;
    }

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Transactional
    void newResume() throws Exception {
        ResumeCreationDto resumeCreationDto = createTestDto();

        MvcResult mvcResult = mockMvc.perform(
                        post("/api/resume/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(resumeCreationDto))
                )
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.id").exists(),
                        jsonPath("$.id").isNumber(),
                        jsonPath("$.summary", Matchers.is("Test summary")),
                        jsonPath("$.status", Matchers.is(ResumeStatus.POSITIVE.toString())),
                        jsonPath("$.skills").isArray(),
                        jsonPath("$.skills").isNotEmpty(),
                        jsonPath("$.skills.length()", Matchers.is(3)),
                        jsonPath("$.skills[*].skill", Matchers.containsInAnyOrder("Java", "Python", "Writing tests")),
                        jsonPath("$.skills[*].skillLevel", Matchers.containsInAnyOrder(SkillLevel.INTERMEDIATE.toString(), SkillLevel.INTERMEDIATE.toString(), SkillLevel.EXPERT.toString())),
                        jsonPath("$.languages.length()", Matchers.is(3)),
                        jsonPath("$.languages").isArray(),
                        jsonPath("$.languages").isNotEmpty(),
                        jsonPath("$.languages[*].language", Matchers.containsInAnyOrder("Dutch", "French", "English")),
                        jsonPath("$.languages[*].skillLevel", Matchers.containsInAnyOrder(SkillLevel.EXPERT.toString(), SkillLevel.BASIC.toString(), SkillLevel.EXPERT.toString())),
                        jsonPath("$.educationList").isArray(),
                        jsonPath("$.educationList").isNotEmpty(),
                        jsonPath("$.educationList.length()", Matchers.is(2)),
                        jsonPath("$.educationList[*].degree", Matchers.containsInAnyOrder("Masters", "Bachelors")),
                        jsonPath("$.educationList[*].program", Matchers.containsInAnyOrder("Applied Engineering", "Applied Engineering")),
                        jsonPath("$.educationList[*].school", Matchers.containsInAnyOrder("UA", "UA")),
                        jsonPath("$.educationList[*].startDate", Matchers.containsInAnyOrder("2020-09-21", "2017-09-21")),
                        jsonPath("$.educationList[*].endDate", Matchers.containsInAnyOrder("2021-06-30", "2020-06-30")),
                        jsonPath("$.educationList[*].description", Matchers.containsInAnyOrder("Desc1", "Desc2")),
                        jsonPath("$.experienceList").isArray(),
                        jsonPath("$.experienceList").isNotEmpty(),
                        jsonPath("$.experienceList.length()", Matchers.is(1)),
                        jsonPath("$.experienceList[0].jobTitle", Matchers.is("Junior Java consultant")),
                        jsonPath("$.experienceList[0].functionCategory", Matchers.is("Software consultant")),
                        jsonPath("$.experienceList[0].company", Matchers.is("Inetum-Realdolmen")),
                        jsonPath("$.experienceList[0].industry", Matchers.is("IT")),
                        jsonPath("$.experienceList[0].startDate", Matchers.is("2021-09-01")),
                        jsonPath("$.experienceList[0].endDate", Matchers.nullValue()),
                        jsonPath("$.experienceList[0].currentJob", Matchers.is(true)),
                        jsonPath("$.experienceList[0].description", Matchers.is("Desc1"))
                )
                .andReturn();

        Long id = Long.valueOf(JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id").toString());

        Resume createdResume = em
                .createQuery("select r from Resume r where r.id = :id", Resume.class)
                .setParameter("id", id)
                .getSingleResult();

        assertAll(
                () -> assertNotNull(createdResume),
                () -> assertEquals("Test summary", createdResume.getSummary()),
                () -> assertEquals(ResumeStatus.POSITIVE, createdResume.getStatus()),
                () -> assertEquals(3, createdResume.getSkills().size()),
                () -> assertEquals(3, createdResume.getLanguages().size()),
                () -> assertEquals(2, createdResume.getEducationList().size()),
                () -> assertEquals(1, createdResume.getExperienceList().size())
        );
    }

    private ResumeCreationDto createTestDto() {
        ResumeCreationDto resumeCreationDto = new ResumeCreationDto();

        resumeCreationDto.setSummary("Test summary");
        resumeCreationDto.setStatus(ResumeStatus.POSITIVE);
        resumeCreationDto.setEducationList(createEducationDtoList());
        resumeCreationDto.setExperienceList(createExperienceDtoList());
        resumeCreationDto.setLanguages(createLanguageDtoList());
        resumeCreationDto.setSkills(createSkillDtoList());

        return resumeCreationDto;
    }

    private List<EducationDto> createEducationDtoList() {
        List<EducationDto> educationDtoList = new ArrayList<>();

        EducationDto educationDto1 = new EducationDto();
        educationDto1.setDegree("Masters");
        educationDto1.setDescription("Desc1");
        educationDto1.setProgram("Applied Engineering");
        educationDto1.setSchool("UA");
        educationDto1.setStartDate(LocalDate.of(2020, 9, 21));
        educationDto1.setEndDate(LocalDate.of(2021, 6, 30));
        educationDtoList.add(educationDto1);

        EducationDto educationDto2 = new EducationDto();
        educationDto2.setDegree("Bachelors");
        educationDto2.setDescription("Desc2");
        educationDto2.setProgram("Applied Engineering");
        educationDto2.setSchool("UA");
        educationDto2.setStartDate(LocalDate.of(2017, 9, 21));
        educationDto2.setEndDate(LocalDate.of(2020, 6, 30));
        educationDtoList.add(educationDto2);

        return educationDtoList;
    }

    private List<ExperienceDto> createExperienceDtoList() {
        List<ExperienceDto> experienceDtoList = new ArrayList<>();

        ExperienceDto experienceDto1 = new ExperienceDto();
        experienceDto1.setCompany("Inetum-Realdolmen");
        experienceDto1.setDescription("Desc1");
        experienceDto1.setCurrentJob(true);
        experienceDto1.setStartDate(LocalDate.of(2021, 9, 1));
        experienceDto1.setFunctionCategory("Software consultant");
        experienceDto1.setIndustry("IT");
        experienceDto1.setJobTitle("Junior Java consultant");
        experienceDtoList.add(experienceDto1);

        return experienceDtoList;
    }

    private List<LanguageDto> createLanguageDtoList() {
        List<LanguageDto> languageDtoList = new ArrayList<>();

        LanguageDto languageDto1 = new LanguageDto();
        languageDto1.setLanguage("Dutch");
        languageDto1.setSkillLevel(SkillLevel.EXPERT);
        languageDtoList.add(languageDto1);

        LanguageDto languageDto2 = new LanguageDto();
        languageDto2.setLanguage("French");
        languageDto2.setSkillLevel(SkillLevel.BASIC);
        languageDtoList.add(languageDto2);

        LanguageDto languageDto3 = new LanguageDto();
        languageDto3.setLanguage("English");
        languageDto3.setSkillLevel(SkillLevel.EXPERT);
        languageDtoList.add(languageDto3);

        return languageDtoList;
    }

    private List<SkillDto> createSkillDtoList() {
        List<SkillDto> skillDtoList = new ArrayList<>();

        SkillDto skillDto1 = new SkillDto();
        skillDto1.setSkill("Java");
        skillDto1.setSkillLevel(SkillLevel.INTERMEDIATE);
        skillDtoList.add(skillDto1);

        SkillDto skillDto2 = new SkillDto();
        skillDto2.setSkill("Python");
        skillDto2.setSkillLevel(SkillLevel.INTERMEDIATE);
        skillDtoList.add(skillDto2);

        SkillDto skillDto3 = new SkillDto();
        skillDto3.setSkill("Writing tests");
        skillDto3.setSkillLevel(SkillLevel.EXPERT);
        skillDtoList.add(skillDto3);

        return skillDtoList;
    }
}

