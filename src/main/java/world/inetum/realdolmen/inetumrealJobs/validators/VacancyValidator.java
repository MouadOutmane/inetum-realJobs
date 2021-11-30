package world.inetum.realdolmen.inetumrealJobs.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;

@Component("beforeCreateVacancyValidator")
public class VacancyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Vacancy.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Vacancy vacancy = (Vacancy) target;
        if (checkInputString(vacancy.getCity())) {
            errors.rejectValue("name", "city.empty");
        }

        if (checkInputString(vacancy.getFunctionTitle())) {
            errors.rejectValue("email", "functionTitle.empty");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

}
