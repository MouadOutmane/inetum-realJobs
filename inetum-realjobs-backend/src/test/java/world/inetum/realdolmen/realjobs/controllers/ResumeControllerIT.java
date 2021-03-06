package world.inetum.realdolmen.realjobs.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import world.inetum.realdolmen.realjobs.BaseIntegrationTest;
import world.inetum.realdolmen.realjobs.InetumRealJobsApplication;
import world.inetum.realdolmen.realjobs.entities.*;
import world.inetum.realdolmen.realjobs.entities.enums.*;
import world.inetum.realdolmen.realjobs.payload.dtos.*;
import world.inetum.realdolmen.realjobs.payload.security.LoginRequest;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = InetumRealJobsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
class ResumeControllerIT extends BaseIntegrationTest {

    private final EntityManager em;

    @Autowired
    ResumeControllerIT(EntityManager em) {
        this.em = em;
    }

    private Account createJobSeekerAndLogin() throws Exception {
        Account account = persistJobSeeker("test@inetum-realdolmen.world", "password");
        LoginRequest request = new LoginRequest("test@inetum-realdolmen.world", "password");
        mockMvc.perform(
                        post("/api/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request))
                )
                .andExpect(status().isOk());
        return account;
    }

    @Test
    void getResume() throws Exception {
        createJobSeekerAndLogin();

        SkillTests.persistSkill("Test 1", SkillLevel.EXPERT);
        SkillTests.persistSkill("Test 2", SkillLevel.BASIC);
        SkillTests.persistSkill("Test 3", SkillLevel.INTERMEDIATE);
        SkillTests.persistSkill("Test 4", SkillLevel.BASIC);

        LanguageTests.persistLanguage("Test 1", LanguageLevel.EXPERT);
        LanguageTests.persistLanguage("Test 2", LanguageLevel.BASIC);
        LanguageTests.persistLanguage("Test 3", LanguageLevel.MOTHER_TONGUE);
        LanguageTests.persistLanguage("Test 4", LanguageLevel.BASIC);

        EducationTests.persistEducation(EducationTests.createEducationCreateDtoList().get(0));
        EducationTests.persistEducation(EducationTests.createEducationCreateDtoList().get(1));

        ExperienceTests.persistExperience(ExperienceTests.createExperienceDtoList().get(0));
        ExperienceTests.persistExperience(ExperienceTests.createExperienceDtoList().get(1));

        SummaryTests.persistSummary("Test 1");

        StatusTests.persistStatus(ResumeStatus.NEUTRAL);

        mockMvc.perform(
                        get("/api/resume/")
                )
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.skills").isArray(),
                        jsonPath("$.skills").isNotEmpty(),
                        jsonPath("$.skills.length()", is(4)),
                        jsonPath("$.skills[*].skill", containsInAnyOrder("Test 1", "Test 2", "Test 3", "Test 4")),
                        jsonPath("$.skills[*].skillLevel", containsInAnyOrder(SkillLevel.EXPERT.toString(), SkillLevel.BASIC.toString(), SkillLevel.INTERMEDIATE.toString(), SkillLevel.BASIC.toString())),
                        jsonPath("$.languages").isArray(),
                        jsonPath("$.languages").isNotEmpty(),
                        jsonPath("$.languages.length()", is(4)),
                        jsonPath("$.languages[*].language", containsInAnyOrder("Test 1", "Test 2", "Test 3", "Test 4")),
                        jsonPath("$.languages[*].languageLevel", containsInAnyOrder(LanguageLevel.EXPERT.toString(), LanguageLevel.BASIC.toString(), LanguageLevel.MOTHER_TONGUE.toString(), LanguageLevel.BASIC.toString())),
                        jsonPath("$.educationList").isArray(),
                        jsonPath("$.educationList").isNotEmpty(),
                        jsonPath("$.educationList.length()", is(2)),
                        jsonPath("$.educationList[*].degree", containsInAnyOrder(Degree.MASTER.toString(), Degree.BACHELOR.toString())),
                        jsonPath("$.educationList[*].program", containsInAnyOrder("Applied Engineering", "Applied Engineering")),
                        jsonPath("$.educationList[*].school", containsInAnyOrder("UA", "UA")),
                        jsonPath("$.educationList[*].startDate", containsInAnyOrder("2020-09-21", "2017-09-21")),
                        jsonPath("$.educationList[*].endDate", containsInAnyOrder("2021-06-30", "2020-06-30")),
                        jsonPath("$.educationList[*].description", containsInAnyOrder("Desc1", "Desc2")),
                        jsonPath("$.experienceList").isArray(),
                        jsonPath("$.experienceList").isNotEmpty(),
                        jsonPath("$.experienceList.length()", is(2)),
                        jsonPath("$.experienceList[*].jobTitle", containsInAnyOrder("Junior Java consultant", "Junior Java consultant")),
                        jsonPath("$.experienceList[*].functionCategory", containsInAnyOrder(FunctionCategory.IT_AND_TELECOMMUNICATIONS.toString(), FunctionCategory.EDUCATION_AND_TRAINING.toString())),
                        jsonPath("$.experienceList[*].company", containsInAnyOrder("Inetum-Realdolmen", "VDAB")),
                        jsonPath("$.experienceList[*].industry", containsInAnyOrder(Industry.COMPUTER.toString(), Industry.COMPUTER.toString())),
                        jsonPath("$.experienceList[*].startDate", containsInAnyOrder("2021-12-01", "2021-09-01")),
                        jsonPath("$.experienceList[*].endDate", containsInAnyOrder(null, "2021-12-01")),
                        jsonPath("$.experienceList[*].currentJob", containsInAnyOrder(true, false)),
                        jsonPath("$.experienceList[*].description", containsInAnyOrder("Desc1", "Desc2")),
                        jsonPath("$.summary").isString(),
                        jsonPath("$.summary", is("Test 1")),
                        jsonPath("$.status").isString(),
                        jsonPath("$.status", is(ResumeStatus.NEUTRAL.toString())),
                        jsonPath("$.accountInfo.email", is("test@inetum-realdolmen.world")),
                        jsonPath("$.accountInfo.firstName", is("first name")),
                        jsonPath("$.accountInfo.lastName", is("last name")),
                        jsonPath("$.accountInfo.mobilePhone", is(nullValue())),
                        jsonPath("$.accountInfo.profilePicture", is("picture"))
                );
    }

    @Nested
    class SkillTests {

        static ResultActions persistSkill(String name, SkillLevel level) throws Exception {
            SkillCreateDto skillCreateDto = new SkillCreateDto();
            skillCreateDto.setSkill(name);
            skillCreateDto.setSkillLevel(level);

            return mockMvc.perform(
                            post("/api/resume/skill")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(skillCreateDto))
                    )
                    .andExpect(status().isOk());
        }

        @Test
        @Transactional
        void addSkill() throws Exception {
            Account account = createJobSeekerAndLogin();

            persistSkill("Test 1", SkillLevel.EXPERT)
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(1)),
                            jsonPath("$[*].skill", containsInAnyOrder("Test 1")),
                            jsonPath("$[*].skillLevel", containsInAnyOrder(SkillLevel.EXPERT.toString()))
                    );
            persistSkill("Test 2", SkillLevel.BASIC)
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(2)),
                            jsonPath("$[*].skill", containsInAnyOrder("Test 1", "Test 2")),
                            jsonPath("$[*].skillLevel", containsInAnyOrder(SkillLevel.EXPERT.toString(), SkillLevel.BASIC.toString()))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(2, resume.getSkills().size(), "The amount of skills isn't the expected amount");
        }

        @Test
        @Transactional
        void removeSkill() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistSkill("Test 1", SkillLevel.EXPERT);
            persistSkill("Test 2", SkillLevel.BASIC);
            persistSkill("Test 3", SkillLevel.INTERMEDIATE);
            String skills = persistSkill("Test 4", SkillLevel.BASIC)
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            List<Skill> skillList = mapper.readValue(skills, new TypeReference<>() {
            });

            Long toRemove = skillList
                    .stream()
                    .filter(i -> i.getSkill().equals("Test 3"))
                    .toList()
                    .get(0)
                    .getId();

            mockMvc.perform(
                            delete("/api/resume/skill/" + toRemove)
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(3)),
                            jsonPath("$[*].skill", containsInAnyOrder("Test 1", "Test 2", "Test 4")),
                            jsonPath("$[*].skill", not(contains("Test 3"))),
                            jsonPath("$[*].skillLevel", containsInAnyOrder(SkillLevel.EXPERT.toString(), SkillLevel.BASIC.toString(), SkillLevel.BASIC.toString())),
                            jsonPath("$[*].skillLevel", not(contains(SkillLevel.INTERMEDIATE.toString())))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(3, resume.getSkills().size(), "The amount of skills isn't the expected amount");
        }

        @Test
        @Transactional
        void getSkills() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistSkill("Test 1", SkillLevel.EXPERT);
            persistSkill("Test 2", SkillLevel.BASIC);
            persistSkill("Test 3", SkillLevel.INTERMEDIATE);
            persistSkill("Test 4", SkillLevel.BASIC);

            mockMvc.perform(
                            get("/api/resume/skill/")
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(4)),
                            jsonPath("$[*].skill", containsInAnyOrder("Test 1", "Test 2", "Test 3", "Test 4")),
                            jsonPath("$[*].skillLevel", containsInAnyOrder(SkillLevel.EXPERT.toString(), SkillLevel.BASIC.toString(), SkillLevel.INTERMEDIATE.toString(), SkillLevel.BASIC.toString()))
                    );
        }
    }

    @Nested
    class LanguageTests {

        static ResultActions persistLanguage(String language, LanguageLevel level) throws Exception {
            LanguageCreateDto languageCreateDto = new LanguageCreateDto();
            languageCreateDto.setLanguage(language);
            languageCreateDto.setLanguageLevel(level);

            return mockMvc.perform(
                            post("/api/resume/language")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(languageCreateDto))
                    )
                    .andExpect(status().isOk());
        }

        @Test
        @Transactional
        void addLanguage() throws Exception {
            Account account = createJobSeekerAndLogin();

            persistLanguage("Test 1", LanguageLevel.EXPERT)
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(1)),
                            jsonPath("$[*].language", containsInAnyOrder("Test 1")),
                            jsonPath("$[*].languageLevel", containsInAnyOrder(LanguageLevel.EXPERT.toString()))
                    );
            persistLanguage("Test 2", LanguageLevel.BASIC)
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(2)),
                            jsonPath("$[*].language", containsInAnyOrder("Test 1", "Test 2")),
                            jsonPath("$[*].languageLevel", containsInAnyOrder(LanguageLevel.EXPERT.toString(), LanguageLevel.BASIC.toString()))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(2, resume.getLanguages().size(), "The amount of languages isn't the expected amount");
        }

        @Test
        @Transactional
        void removeLanguage() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistLanguage("Test 1", LanguageLevel.EXPERT);
            persistLanguage("Test 2", LanguageLevel.BASIC);
            persistLanguage("Test 3", LanguageLevel.MOTHER_TONGUE);
            String languages = persistLanguage("Test 4", LanguageLevel.BASIC)
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            List<Language> languageList = mapper.readValue(languages, new TypeReference<>() {
            });

            Long toRemove = languageList
                    .stream()
                    .filter(i -> i.getLanguage().equals("Test 3"))
                    .toList()
                    .get(0)
                    .getId();

            mockMvc.perform(
                            delete("/api/resume/language/" + toRemove)
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(3)),
                            jsonPath("$[*].language", containsInAnyOrder("Test 1", "Test 2", "Test 4")),
                            jsonPath("$[*].language", not(contains("Test 3"))),
                            jsonPath("$[*].languageLevel", containsInAnyOrder(LanguageLevel.EXPERT.toString(), LanguageLevel.BASIC.toString(), LanguageLevel.BASIC.toString())),
                            jsonPath("$[*].languageLevel", not(contains(LanguageLevel.MOTHER_TONGUE.toString())))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(3, resume.getLanguages().size(), "The amount of languages isn't the expected amount");
        }

        @Test
        @Transactional
        void getLanguages() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistLanguage("Test 1", LanguageLevel.EXPERT);
            persistLanguage("Test 2", LanguageLevel.BASIC);
            persistLanguage("Test 3", LanguageLevel.MOTHER_TONGUE);
            persistLanguage("Test 4", LanguageLevel.BASIC);

            mockMvc.perform(
                            get("/api/resume/language/")
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(4)),
                            jsonPath("$[*].language", containsInAnyOrder("Test 1", "Test 2", "Test 3", "Test 4")),
                            jsonPath("$[*].languageLevel", containsInAnyOrder(LanguageLevel.EXPERT.toString(), LanguageLevel.BASIC.toString(), LanguageLevel.MOTHER_TONGUE.toString(), LanguageLevel.BASIC.toString()))
                    );
        }
    }

    @Nested
    class EducationTests {

        static ResultActions persistEducation(EducationCreateDto educationCreateDto) throws Exception {
            return mockMvc.perform(
                            post("/api/resume/education")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(educationCreateDto))
                    )
                    .andExpect(status().isOk());
        }

        static List<EducationCreateDto> createEducationCreateDtoList() {
            List<EducationCreateDto> educationDtoList = new ArrayList<>();

            EducationCreateDto educationDto1 = new EducationCreateDto();
            educationDto1.setDegree(Degree.MASTER);
            educationDto1.setDescription("Desc1");
            educationDto1.setProgram("Applied Engineering");
            educationDto1.setSchool("UA");
            educationDto1.setStartDate(LocalDate.of(2020, 9, 21));
            educationDto1.setEndDate(LocalDate.of(2021, 6, 30));
            educationDtoList.add(educationDto1);

            EducationCreateDto educationDto2 = new EducationCreateDto();
            educationDto2.setDegree(Degree.BACHELOR);
            educationDto2.setDescription("Desc2");
            educationDto2.setProgram("Applied Engineering");
            educationDto2.setSchool("UA");
            educationDto2.setStartDate(LocalDate.of(2017, 9, 21));
            educationDto2.setEndDate(LocalDate.of(2020, 6, 30));
            educationDtoList.add(educationDto2);

            return educationDtoList;
        }

        @Test
        @Transactional
        void addEducation() throws Exception {
            Account account = createJobSeekerAndLogin();

            persistEducation(createEducationCreateDtoList().get(0))
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(1)),
                            jsonPath("$[*].degree", containsInAnyOrder(Degree.MASTER.toString())),
                            jsonPath("$[*].program", containsInAnyOrder("Applied Engineering")),
                            jsonPath("$[*].school", containsInAnyOrder("UA")),
                            jsonPath("$[*].startDate", containsInAnyOrder("2020-09-21")),
                            jsonPath("$[*].endDate", containsInAnyOrder("2021-06-30")),
                            jsonPath("$[*].description", containsInAnyOrder("Desc1"))
                    );
            persistEducation(createEducationCreateDtoList().get(1))
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(2)),
                            jsonPath("$[*].degree", containsInAnyOrder(Degree.MASTER.toString(), Degree.BACHELOR.toString())),
                            jsonPath("$[*].program", containsInAnyOrder("Applied Engineering", "Applied Engineering")),
                            jsonPath("$[*].school", containsInAnyOrder("UA", "UA")),
                            jsonPath("$[*].startDate", containsInAnyOrder("2020-09-21", "2017-09-21")),
                            jsonPath("$[*].endDate", containsInAnyOrder("2021-06-30", "2020-06-30")),
                            jsonPath("$[*].description", containsInAnyOrder("Desc1", "Desc2"))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(2, resume.getEducationList().size(), "The amount of education entries isn't the expected amount");
        }

        @Test
        @Transactional
        void editEducation() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistEducation(createEducationCreateDtoList().get(0));
            String educationString = persistEducation(createEducationCreateDtoList().get(1))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            List<Education> educationList = mapper.readValue(educationString, new TypeReference<>() {
            });

            Long toEdit = educationList
                    .stream()
                    .filter(i -> i.getDegree().equals(Degree.MASTER))
                    .toList()
                    .get(0)
                    .getId();

            EducationEditDto editDto = new EducationEditDto();
            editDto.setId(toEdit);
            editDto.setDegree(Degree.PHD);
            editDto.setDescription("Desc1 - edit");
            editDto.setProgram("Applied Engineering 2");
            editDto.setSchool("UA (NL)");
            editDto.setStartDate(LocalDate.of(2020, 9, 22));
            editDto.setEndDate(LocalDate.of(2021, 6, 29));

            mockMvc.perform(
                            post("/api/resume/education/edit")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(editDto))
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(2)),
                            jsonPath("$[*].degree", containsInAnyOrder(Degree.PHD.toString(), Degree.BACHELOR.toString())),
                            jsonPath("$[*].program", containsInAnyOrder("Applied Engineering 2", "Applied Engineering")),
                            jsonPath("$[*].school", containsInAnyOrder("UA (NL)", "UA")),
                            jsonPath("$[*].startDate", containsInAnyOrder("2020-09-22", "2017-09-21")),
                            jsonPath("$[*].endDate", containsInAnyOrder("2021-06-29", "2020-06-30")),
                            jsonPath("$[*].description", containsInAnyOrder("Desc1 - edit", "Desc2"))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(2, resume.getEducationList().size(), "The amount of experience entries isn't the expected amount");

            Education education = em
                    .createQuery("select e from Education e where e.id = :id", Education.class)
                    .setParameter("id", toEdit)
                    .getSingleResult();

            assertAll(
                    () -> assertEquals("Applied Engineering 2", education.getProgram(), "The program wasn't edited"),
                    () -> assertEquals("Desc1 - edit", education.getDescription(), "The description wasn't edited"),
                    () -> assertEquals(LocalDate.of(2020, 9, 22), education.getStartDate(), "The start date wasn't edited"),
                    () -> assertEquals(LocalDate.of(2021, 6, 29), education.getEndDate(), "The end date wasn't edited"),
                    () -> assertEquals(Degree.PHD, education.getDegree(), "The degree category wasn't edited"),
                    () -> assertEquals("UA (NL)", education.getSchool(), "The school wasn't edited")
            );
        }

        @Test
        @Transactional
        void removeEducation() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistEducation(createEducationCreateDtoList().get(0));
            String educationString = persistEducation(createEducationCreateDtoList().get(1))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            List<Education> educationList = mapper.readValue(educationString, new TypeReference<>() {
            });

            Long toRemove = educationList
                    .stream()
                    .filter(i -> i.getDegree().equals(Degree.BACHELOR))
                    .toList()
                    .get(0)
                    .getId();

            mockMvc.perform(
                            delete("/api/resume/education/" + toRemove)
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(1)),
                            jsonPath("$[*].degree", containsInAnyOrder(Degree.MASTER.toString())),
                            jsonPath("$[*].program", containsInAnyOrder("Applied Engineering")),
                            jsonPath("$[*].school", containsInAnyOrder("UA")),
                            jsonPath("$[*].startDate", containsInAnyOrder("2020-09-21")),
                            jsonPath("$[*].endDate", containsInAnyOrder("2021-06-30")),
                            jsonPath("$[*].description", containsInAnyOrder("Desc1"))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(1, resume.getEducationList().size(), "The amount of education entries isn't the expected amount");
        }

        @Test
        @Transactional
        void getEducationList() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistEducation(createEducationCreateDtoList().get(0));
            persistEducation(createEducationCreateDtoList().get(1));

            mockMvc.perform(
                            get("/api/resume/education/")
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(2)),
                            jsonPath("$[*].degree", containsInAnyOrder(Degree.MASTER.toString(), Degree.BACHELOR.toString())),
                            jsonPath("$[*].program", containsInAnyOrder("Applied Engineering", "Applied Engineering")),
                            jsonPath("$[*].school", containsInAnyOrder("UA", "UA")),
                            jsonPath("$[*].startDate", containsInAnyOrder("2020-09-21", "2017-09-21")),
                            jsonPath("$[*].endDate", containsInAnyOrder("2021-06-30", "2020-06-30")),
                            jsonPath("$[*].description", containsInAnyOrder("Desc1", "Desc2")),
                            jsonPath("$[*].version", containsInAnyOrder(0, 0))
                    );
        }
    }

    @Nested
    class ExperienceTests {

        static ResultActions persistExperience(ExperienceCreateDto experienceCreateDto) throws Exception {
            return mockMvc.perform(
                            post("/api/resume/experience")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(experienceCreateDto))
                    )
                    .andExpect(status().isOk());
        }

        static List<ExperienceCreateDto> createExperienceDtoList() {
            List<ExperienceCreateDto> experienceDtoList = new ArrayList<>();

            ExperienceCreateDto experienceDto1 = new ExperienceCreateDto();
            experienceDto1.setCompany("Inetum-Realdolmen");
            experienceDto1.setDescription("Desc1");
            experienceDto1.setCurrentJob(true);
            experienceDto1.setStartDate(LocalDate.of(2021, 12, 1));
            experienceDto1.setFunctionCategory(FunctionCategory.IT_AND_TELECOMMUNICATIONS);
            experienceDto1.setIndustry(Industry.COMPUTER);
            experienceDto1.setJobTitle("Junior Java consultant");
            experienceDtoList.add(experienceDto1);

            ExperienceCreateDto experienceDto2 = new ExperienceCreateDto();
            experienceDto2.setCompany("VDAB");
            experienceDto2.setDescription("Desc2");
            experienceDto2.setCurrentJob(false);
            experienceDto2.setStartDate(LocalDate.of(2021, 9, 1));
            experienceDto2.setEndDate(LocalDate.of(2021, 12, 1));
            experienceDto2.setFunctionCategory(FunctionCategory.EDUCATION_AND_TRAINING);
            experienceDto2.setIndustry(Industry.COMPUTER);
            experienceDto2.setJobTitle("Junior Java consultant");
            experienceDtoList.add(experienceDto2);

            return experienceDtoList;
        }

        @Test
        @Transactional
        void addExperience() throws Exception {
            Account account = createJobSeekerAndLogin();

            persistExperience(createExperienceDtoList().get(0))
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(1)),
                            jsonPath("$[0].jobTitle", is("Junior Java consultant")),
                            jsonPath("$[0].functionCategory", is(FunctionCategory.IT_AND_TELECOMMUNICATIONS.toString())),
                            jsonPath("$[0].company", is("Inetum-Realdolmen")),
                            jsonPath("$[0].industry", is(Industry.COMPUTER.toString())),
                            jsonPath("$[0].startDate", is("2021-12-01")),
                            jsonPath("$[0].endDate", nullValue()),
                            jsonPath("$[0].currentJob", is(true)),
                            jsonPath("$[0].description", is("Desc1"))
                    );
            persistExperience(createExperienceDtoList().get(1))
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(2)),
                            jsonPath("$[*].jobTitle", containsInAnyOrder("Junior Java consultant", "Junior Java consultant")),
                            jsonPath("$[*].functionCategory", containsInAnyOrder(FunctionCategory.IT_AND_TELECOMMUNICATIONS.toString(), FunctionCategory.EDUCATION_AND_TRAINING.toString())),
                            jsonPath("$[*].company", containsInAnyOrder("Inetum-Realdolmen", "VDAB")),
                            jsonPath("$[*].industry", containsInAnyOrder(Industry.COMPUTER.toString(), Industry.COMPUTER.toString())),
                            jsonPath("$[*].startDate", containsInAnyOrder("2021-12-01", "2021-09-01")),
                            jsonPath("$[*].endDate", containsInAnyOrder(null, "2021-12-01")),
                            jsonPath("$[*].currentJob", containsInAnyOrder(true, false)),
                            jsonPath("$[*].description", containsInAnyOrder("Desc1", "Desc2"))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(2, resume.getExperienceList().size(), "The amount of experience entries isn't the expected amount");
        }

        @Test
        @Transactional
        void editExperience() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistExperience(createExperienceDtoList().get(0));
            String experienceString = persistExperience(createExperienceDtoList().get(1))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            List<Experience> experienceList = mapper.readValue(experienceString, new TypeReference<>() {
            });

            Long toEdit = experienceList
                    .stream()
                    .filter(i -> i.getCompany().equals("Inetum-Realdolmen"))
                    .toList()
                    .get(0)
                    .getId();

            ExperienceEditDto editDto = new ExperienceEditDto();
            editDto.setId(toEdit);
            editDto.setCompany("Inetum-Realdolmen-test");
            editDto.setDescription("Desc1 - edited");
            editDto.setCurrentJob(false);
            editDto.setStartDate(LocalDate.of(2021, 12, 2));
            editDto.setEndDate(LocalDate.of(2021, 12, 3));
            editDto.setFunctionCategory(FunctionCategory.GENERAL_MANAGEMENT);
            editDto.setIndustry(Industry.AEROSPACE);
            editDto.setJobTitle("Aerospace manager");

            mockMvc.perform(
                            post("/api/resume/experience/edit")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(editDto))
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(2)),
                            jsonPath("$[*].jobTitle", containsInAnyOrder("Aerospace manager", "Junior Java consultant")),
                            jsonPath("$[*].functionCategory", containsInAnyOrder(FunctionCategory.GENERAL_MANAGEMENT.toString(), FunctionCategory.EDUCATION_AND_TRAINING.toString())),
                            jsonPath("$[*].company", containsInAnyOrder("Inetum-Realdolmen-test", "VDAB")),
                            jsonPath("$[*].industry", containsInAnyOrder(Industry.AEROSPACE.toString(), Industry.COMPUTER.toString())),
                            jsonPath("$[*].startDate", containsInAnyOrder("2021-12-02", "2021-09-01")),
                            jsonPath("$[*].endDate", containsInAnyOrder("2021-12-03", "2021-12-01")),
                            jsonPath("$[*].currentJob", containsInAnyOrder(false, false)),
                            jsonPath("$[*].description", containsInAnyOrder("Desc1 - edited", "Desc2"))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(2, resume.getExperienceList().size(), "The amount of experience entries isn't the expected amount");

            Experience experience = em
                    .createQuery("select e from Experience e where e.id = :id", Experience.class)
                    .setParameter("id", toEdit)
                    .getSingleResult();

            assertAll(
                    () -> assertEquals("Inetum-Realdolmen-test", experience.getCompany(), "The company wasn't edited"),
                    () -> assertEquals("Desc1 - edited", experience.getDescription(), "The description wasn't edited"),
                    () -> assertFalse(experience.isCurrentJob(), "Current job wasn't edited"),
                    () -> assertEquals(LocalDate.of(2021, 12, 2), experience.getStartDate(), "The start date wasn't edited"),
                    () -> assertEquals(LocalDate.of(2021, 12, 3), experience.getEndDate(), "The end date wasn't edited"),
                    () -> assertEquals(FunctionCategory.GENERAL_MANAGEMENT, experience.getFunctionCategory(), "The function category wasn't edited"),
                    () -> assertEquals(Industry.AEROSPACE, experience.getIndustry(), "The industry wasn't edited"),
                    () -> assertEquals("Aerospace manager", experience.getJobTitle(), "The job title wasn't edited")
            );
        }

        @Test
        @Transactional
        void removeExperience() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistExperience(createExperienceDtoList().get(0));
            String languages = persistExperience(createExperienceDtoList().get(1))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            List<Experience> experienceList = mapper.readValue(languages, new TypeReference<>() {
            });

            Long toRemove = experienceList
                    .stream()
                    .filter(i -> i.getCompany().equals("VDAB"))
                    .toList()
                    .get(0)
                    .getId();

            mockMvc.perform(
                            delete("/api/resume/experience/" + toRemove)
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(1)),
                            jsonPath("$[0].jobTitle", is("Junior Java consultant")),
                            jsonPath("$[0].functionCategory", is(FunctionCategory.IT_AND_TELECOMMUNICATIONS.toString())),
                            jsonPath("$[0].company", is("Inetum-Realdolmen")),
                            jsonPath("$[0].industry", is(Industry.COMPUTER.toString())),
                            jsonPath("$[0].startDate", is("2021-12-01")),
                            jsonPath("$[0].endDate", nullValue()),
                            jsonPath("$[0].currentJob", is(true)),
                            jsonPath("$[0].description", is("Desc1"))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals(1, resume.getExperienceList().size(), "The amount of experience entries isn't the expected amount");
        }

        @Test
        @Transactional
        void getExperienceList() throws Exception {
            Account account = createJobSeekerAndLogin();
            persistExperience(createExperienceDtoList().get(0));
            persistExperience(createExperienceDtoList().get(1));

            mockMvc.perform(
                            get("/api/resume/experience/")
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isArray(),
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$.length()", is(2)),
                            jsonPath("$[*].jobTitle", containsInAnyOrder("Junior Java consultant", "Junior Java consultant")),
                            jsonPath("$[*].functionCategory", containsInAnyOrder(FunctionCategory.IT_AND_TELECOMMUNICATIONS.toString(), FunctionCategory.EDUCATION_AND_TRAINING.toString())),
                            jsonPath("$[*].company", containsInAnyOrder("Inetum-Realdolmen", "VDAB")),
                            jsonPath("$[*].industry", containsInAnyOrder(Industry.COMPUTER.toString(), Industry.COMPUTER.toString())),
                            jsonPath("$[*].startDate", containsInAnyOrder("2021-12-01", "2021-09-01")),
                            jsonPath("$[*].endDate", containsInAnyOrder(null, "2021-12-01")),
                            jsonPath("$[*].currentJob", containsInAnyOrder(true, false)),
                            jsonPath("$[*].description", containsInAnyOrder("Desc1", "Desc2")),
                            jsonPath("$[*].version", containsInAnyOrder(0, 0))
                    );
        }
    }

    @Nested
    class SummaryTests {

        static ResultActions persistSummary(String summary) throws Exception {
            SingleValueDto<String> summaryDto = new SingleValueDto<>();
            summaryDto.setValue(summary);

            return mockMvc.perform(
                            post("/api/resume/summary")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(summaryDto))
                    )
                    .andExpect(status().isOk());
        }

        @Test
        @Transactional
        void setSummary() throws Exception {
            Account account = createJobSeekerAndLogin();

            persistSummary("Test 1")
                    .andExpectAll(
                            jsonPath("$.value").isString(),
                            jsonPath("$.value", is("Test 1"))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume, "No resume found in the database");
            assertEquals("Test 1", resume.getSummary(), "The summary wasn't set correctly");
        }

        @Test
        @Transactional
        void getSummary() throws Exception {
            createJobSeekerAndLogin();
            persistSummary("Test 1");

            mockMvc.perform(
                            get("/api/resume/summary/")
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$.value").isString(),
                            jsonPath("$.value", is("Test 1"))
                    );
        }
    }

    @Nested
    class StatusTests {

        static ResultActions persistStatus(ResumeStatus status) throws Exception {
            SingleValueDto<ResumeStatus> statusDto = new SingleValueDto<>();
            statusDto.setValue(status);

            return mockMvc.perform(
                            post("/api/resume/status")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(statusDto))
                    )
                    .andExpect(status().isOk());
        }

        @Test
        @Transactional
        void setStatus() throws Exception {
            Account account = createJobSeekerAndLogin();

            persistStatus(ResumeStatus.POSITIVE)
                    .andExpectAll(
                            jsonPath("$.value").isString(),
                            jsonPath("$.value", is(ResumeStatus.POSITIVE.toString()))
                    );

            JobSeeker jobSeeker = em
                    .createQuery("select j from JobSeeker j where j.id = :id", JobSeeker.class)
                    .setParameter("id", account.getId())
                    .getSingleResult();

            Resume resume = jobSeeker.getResume();

            assertNotNull(resume);
            assertEquals(ResumeStatus.POSITIVE, resume.getStatus(), "The status wasn't set correctly");
        }

        @Test
        @Transactional
        void getStatus() throws Exception {
            createJobSeekerAndLogin();
            persistStatus(ResumeStatus.NEUTRAL);

            mockMvc.perform(
                            get("/api/resume/status/")
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$.value").isString(),
                            jsonPath("$.value", is(ResumeStatus.NEUTRAL.toString()))
                    );
        }

        @Test
        void getAccount() throws Exception {
            Account account = createJobSeekerAndLogin();

            mockMvc.perform(
                            get("/api/resume/account")
                    )
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$.email", is("test@inetum-realdolmen.world")),
                            jsonPath("$.firstName", is("first name")),
                            jsonPath("$.lastName", is("last name")),
                            jsonPath("$.mobilePhone", is(nullValue())),
                            jsonPath("$.profilePicture", is("picture"))
                    );
        }
    }
}