package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.springframework.data.jpa.domain.Specification;
import world.inetum.realdolmen.inetumrealJobs.jpa.Vacancy;

import java.util.Locale;

public class VacancySpecification {

    public static Specification<Vacancy> withFunctionTitle(String functionTitle){
        return ((vacancy, query, builder) ->
                builder.like(
                    builder.lower(
                        vacancy.get("functionTitle")
                    ), "%" + functionTitle.toLowerCase() +"%"
                )
        );
    }

    public static Specification<Vacancy> withCountry(String country){
        return ((vacancy, query, builder) ->
                builder.like(
                        builder.lower(
                                vacancy.get("country")
                        ), country.toLowerCase()
                )
        );
    }

    public static Specification<Vacancy> withContractType(String contractType){
        return ((vacancy, query, builder) ->
                builder.like(
                        builder.lower(
                                vacancy.get("contractType")
                        ), "%" + contractType.toLowerCase() +"%"
                )
        );
    }

    public static Specification<Vacancy> withIndustry(String industry){
        return ((vacancy, query, builder) ->
                builder.like(
                        builder.lower(
                                vacancy.get("industry")
                        ), "%" + industry.toLowerCase() +"%"
                )
        );
    }

    public static Specification<Vacancy> withRequiredYearsOfExperience(Integer requiredYearsOfExperience){
        return ((vacancy, query, builder) -> builder.le(
                vacancy.get("requiredYearsOfExperience"), requiredYearsOfExperience
        )
        );
    }
}

