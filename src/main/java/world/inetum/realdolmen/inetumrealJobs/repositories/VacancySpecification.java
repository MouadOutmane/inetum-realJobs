package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;

public class VacancySpecification {

    public static Specification<Vacancy> withFunctionTitle(String functionTitle) {
        if (!StringUtils.hasText(functionTitle)) {
            return ((vacancy, query, builder) -> builder.and());
        } else {
            return ((vacancy, query, builder) ->
                    builder.like(
                            builder.lower(
                                    vacancy.get("functionTitle")
                            ), "%" + functionTitle.toLowerCase() + "%"
                    )
            );
        }
    }

    public static Specification<Vacancy> withCountry(String country) {
        if (!StringUtils.hasText(country)) {
            return ((vacancy, query, builder) -> builder.and());
        } else {
            return ((vacancy, query, builder) ->
                    builder.like(
                            builder.lower(
                                    vacancy.get("country")
                            ), country.toLowerCase()
                    )
            );
        }
    }

    public static Specification<Vacancy> withContractType(String contractType) {
        if (!StringUtils.hasText(contractType)) {
            return ((vacancy, query, builder) -> builder.and());
        } else {
            return ((vacancy, query, builder) ->
                    builder.like(
                            builder.lower(
                                    vacancy.get("contractType")
                            ), "%" + contractType.toLowerCase() + "%"
                    )
            );
        }
    }

    public static Specification<Vacancy> withIndustry(String industry) {
        if (!StringUtils.hasText(industry)) {
            return ((vacancy, query, builder) -> builder.and());
        } else {
            return ((vacancy, query, builder) -> builder.like(builder.lower(vacancy.get("industry")), "%" + industry.toLowerCase() + "%"));
        }
    }

    public static Specification<Vacancy> withRequiredYearsOfExperience(Integer requiredYearsOfExperience) {
        return ((vacancy, query, builder) -> builder.le(
                vacancy.get("requiredYearsOfExperience"), requiredYearsOfExperience
        )
        );
    }
}

