package world.inetum.realdolmen.inetumrealJobs.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "function_title")
    @NotEmpty(message = "Please provide a function title")
    private String functionTitle;

    @Column(name = "contract_type")
    @NotEmpty(message = "Please provide a contract type")
    private String contractType;

    @Column(name = "function_description")
    @NotEmpty(message = "Please provide a function description")
    private String functionDescription;

    @Column(name = "posting_date")
    private String postingDate;

    @Column(name = "company_name")
    @NotEmpty(message = "Please provide a company name")
    private String companyName;

    @Column(name = "industry")
    private String industry;

    @Column(name = "country")
    @NotEmpty(message = "Please provide a country")
    private String country;

    @Column(name = "city")
    @NotEmpty(message = "Please provide a city")
    private String city;

    @Column(name = "postal_code")
    @NotNull(message = "Please provide a postal code")
    @Positive(message = "Please provide a valid postal code")
    private Integer postalCode;

    @Column(name = "street_name")
    @NotEmpty(message = "Please provide a street name")
    private String streetName;

    @Column(name = "nr")
    @NotNull(message = "Please provide a street number")
    @Positive(message = "Please provide a valid street number")
    private Integer nr;

    @Column(name = "box")
    private String box;

    @Column(name = "required_years_of_experience")
    @NotNull(message = "Please provide a number min of required years of experience")
    @PositiveOrZero(message = "Please provide a positive (not zero) number min of required years of experience")
    private Integer requiredYearsOfExperience;

    @Column(name = "required_experience_skills_education")
    private String requiredExperienceSkillsEducation;

    @Column(name = "offer")
    @NotEmpty(message = "Please provide an offer")
    private String offer;

    public Vacancy(String functionTitle, String contractType, String functionDescription, String companyName, String industry, String country, String city, Integer postalCode, String streetName, Integer nr, Integer requiredYearsOfExperience, String offer) {
        this.functionTitle = functionTitle;
        this.contractType = contractType;
        this.functionDescription = functionDescription;
        this.companyName = companyName;
        this.industry = industry;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.nr = nr;
        this.requiredYearsOfExperience = requiredYearsOfExperience;
        this.offer = offer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionTitle() {
        return functionTitle;
    }

    public void setFunctionTitle(String functionTilte) {
        this.functionTitle = functionTilte;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public Integer getRequiredYearsOfExperience() {
        return requiredYearsOfExperience;
    }

    public void setRequiredYearsOfExperience(Integer requiredYearsOfExperience) {
        this.requiredYearsOfExperience = requiredYearsOfExperience;
    }

    public String getRequiredExperienceSkillsEducation() {
        return requiredExperienceSkillsEducation;
    }

    public void setRequiredExperienceSkillsEducation(String requiredExperienceSkillsEducation) {
        this.requiredExperienceSkillsEducation = requiredExperienceSkillsEducation;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String toJson() {

        return "{\"functionTitle\": \"" +
                this.functionTitle + "\"" +
                ",\"contractType\": \"" +
                this.contractType + "\"" +
                ",\"offer\": \"" +
                this.offer + "\"" +
                ",\"postalCode\": \"" +
                this.postalCode + "\"" +
                ",\"requiredYearsOfExperience\": \"" +
                this.requiredYearsOfExperience + "\"" +
                ",\"city\": \"" +
                this.city + "\"" +
                ",\"country\": \"" +
                this.country + "\"" +
                ",\"nr\": \"" +
                this.nr + "\"" +
                ",\"streetName\": \"" +
                this.streetName + "\"" +
                ",\"requiredExperienceSkillsEducation\": \"" +
                this.requiredExperienceSkillsEducation + "\"" +
                ",\"functionDescription\": \"" +
                this.functionDescription + "\"" +
                ",\"companyName\": \"" +
                this.companyName + "\"" +
                ",\"industry\": \"" +
                this.industry + "\"" +
                ",\"box\": \"" +
                this.box + "\"}";
    }
}
