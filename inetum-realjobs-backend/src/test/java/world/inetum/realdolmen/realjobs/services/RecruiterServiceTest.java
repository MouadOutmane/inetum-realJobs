package world.inetum.realdolmen.realjobs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    List<Vacancy> sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vacancy = new Vacancy();
        vacancyList = List.of(vacancy);
    }

    @Test
    @DisplayName("Get list of vacancies from vacancy service")
    void testFindAll() {
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
}