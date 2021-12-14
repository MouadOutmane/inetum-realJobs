package world.inetum.realdolmen.inetumrealJobs.validators;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;

@Component("beforeCreateVacancyValidator")
public class VacancyValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Vacancy.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        Vacancy vacancy = (Vacancy) target;
        if (isNullOrEmpty(vacancy.getCity())) {
            errors.rejectValue("name", "city.empty");
        }

        if (isNullOrEmpty(vacancy.getFunctionTitle())) {
            errors.rejectValue("email", "functionTitle.empty");
        }
    }

    private boolean isNullOrEmpty(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
