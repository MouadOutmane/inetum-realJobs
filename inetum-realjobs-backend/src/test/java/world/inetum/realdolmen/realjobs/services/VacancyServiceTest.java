package world.inetum.realdolmen.realjobs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.repositories.VacancyRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VacancyServiceTest {

    @Mock
    VacancyRepository vacancyRepository;
    @InjectMocks
    VacancyService vacancyService;

    Vacancy vacancy;
    List<Vacancy> vacancyList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get list of all vacancies")
    void testFindAll() {
        vacancy = new Vacancy();
        vacancyList = List.of(vacancy);
        when(vacancyRepository.findAll()).thenReturn(vacancyList);
        List<Vacancy> vacancies = vacancyService.findAll();
        assertNotNull(vacancies);
        assertFalse(vacancies.isEmpty());
        assertEquals(vacancies.size(), 1);
    }

    @Test
    @DisplayName(("Find all does not throw exception"))
    void testFindAll_doesNotThrowException() {
        when(vacancyRepository.findAll()).thenReturn(null);
        assertNull(vacancyService.findAll());
        assertDoesNotThrow(
                () -> vacancyService.findAll()
        );
        vacancyList = Collections.emptyList();
        assertEquals(0, vacancyList.size());
    }

    @Test
    @DisplayName("Find all throws exception")
    void testFindAll_throwsException() {
        when(vacancyRepository.findAll()).thenReturn(null);
        assertThrows(NullPointerException.class,
                () -> vacancyService.findAll().size());
    }
}