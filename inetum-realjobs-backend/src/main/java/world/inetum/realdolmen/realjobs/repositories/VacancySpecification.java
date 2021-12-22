package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.entities.enums.ContractType;

public class VacancySpecification {

    public static Specification<Vacancy> withFunctionTitle(String functionTitle) {
        if (!StringUtils.hasText(functionTitle)) {
            return null;
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

    public static Specification<Vacancy> withCountry(Long country_id) {
        if (country_id == null) {
            return null;
        } else {
            return ((vacancy, query, builder) ->
                    builder.equal(
                            vacancy.get("address").get("country").get("id"),
                            country_id
                    )
            );
        }
    }

    public static Specification<Vacancy> withContractType(ContractType contractType) {
        if (contractType == null) {
            return null;
        } else {
            return ((vacancy, query, builder) ->
                    builder.equal(
                            vacancy.get("contractType"),
                            contractType
                    )
            );
        }
    }

    public static Specification<Vacancy> withIndustry(String industry) {
        if (!StringUtils.hasText(industry)) {
            return null;
        } else {
            return ((vacancy, query, builder) ->
                    builder.like(
                            builder.lower(
                                    vacancy.get("company").get("industry")
                            ),
                            "%" + industry.toLowerCase() + "%")
            );
        }
    }

    public static Specification<Vacancy> withRequiredYearsOfExperience(Integer requiredYearsOfExperience) {
        if (requiredYearsOfExperience == null) {
            return null;
        } else {
            return ((vacancy, query, builder) ->
                    builder.le(
                            vacancy.get("requiredYearsOfExperience"), requiredYearsOfExperience
                    )
            );
        }
    }

}

