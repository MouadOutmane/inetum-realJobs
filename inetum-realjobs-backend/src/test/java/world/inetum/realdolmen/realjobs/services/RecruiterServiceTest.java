package world.inetum.realdolmen.realjobs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import world.inetum.realdolmen.realjobs.entities.Recruiter;
import world.inetum.realdolmen.realjobs.entities.Vacancy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RecruiterServiceTest {
    @Mock
    VacancyService vacancyService;
    @Mock
    ApplicationService applicationService;
    @InjectMocks
    RecruiterService recruiterService;
    Vacancy vacancy;
    List<Vacancy> vacancyList;
    Integer amountOfApplicants;
    Recruiter recruiter;
    List<Vacancy> sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get list of vacancies from vacancy service")
    void testFindAll() {
        vacancy = new Vacancy();
        vacancyList = List.of(vacancy);
        when(vacancyService.findAll()).thenReturn(vacancyList);
        sut = recruiterService.findAll();
        assertNotNull(sut);
        assertFalse(sut.isEmpty());
        assertEquals(sut.size(), 1);
    }

    @Test
    @DisplayName("Get count of applicants from application service")
    void testGetAmountOfApplicants() {
        when(applicationService.getAmountOfApplicants(anyLong())).thenReturn(5);
        amountOfApplicants = recruiterService.getAmountOfApplicants(1L);
        assertTrue(amountOfApplicants > 0);
        assertEquals(5, amountOfApplicants);
        assertNotNull(amountOfApplicants);
    }

    @Test
    @DisplayName("Find all does not throw exception")
    void testFindAll_doesNotThrowException() {
        when(vacancyService.findAll()).thenReturn(null);
        sut = recruiterService.findAll();
        assertNull(sut);
        assertDoesNotThrow(() -> recruiterService.findAll());
    }

    @Test
    @DisplayName("Find all throws exception")
    void testFindAll_throwsException() {
        when(vacancyService.findAll()).thenReturn(null);
        sut = recruiterService.findAll();
        assertNull(sut);
        assertThrows(NullPointerException.class, () -> recruiterService.findAll().size());
    }

    @Test
    @DisplayName("Get list of vacancies for specific recruiter")
    void testFindAllVacanciesByRecruiterId() {
        Vacancy firstVacancy = new Vacancy();
        recruiter = new Recruiter();
        recruiter.setId(1L);
        firstVacancy.setRecruiter(recruiter);
        Vacancy secondVacancy = new Vacancy();
        secondVacancy.setRecruiter(recruiter);
        Vacancy thirdVacancy = new Vacancy();
        recruiter = new Recruiter();
        recruiter.setId(2L);
        thirdVacancy.setRecruiter(recruiter);
        vacancyList = List.of(firstVacancy, secondVacancy);
        when(vacancyService.getVacanciesByRecruiterId(anyLong())).thenReturn(vacancyList);
        sut = recruiterService.findAllVacanciesByRecruiterId(1L);
        assertNotNull(sut);
        assertEquals(2, sut.size());
        assertEquals(1L, sut.get(sut.indexOf(firstVacancy)).getRecruiter().getId());
        assertEquals(1L, sut.get(sut.indexOf(secondVacancy)).getRecruiter().getId());
    }
}