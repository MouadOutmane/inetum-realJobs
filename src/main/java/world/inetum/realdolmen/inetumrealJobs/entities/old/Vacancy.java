package world.inetum.realdolmen.inetumrealJobs.entities.old;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vacancy_old")
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "function_title")
    private String functionTitle;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "function_description")
    private String functionDescription;

    @Column(name = "posting_date")
    private String postingDate;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "industry")
    private String industry;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private Integer postalCode;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "nr")
    private Integer nr;

    @Column(name = "box")
    private String box;

    @Column(name = "required_years_of_experience")
    private Integer requiredYearsOfExperience;

    @Column(name = "required_experience_skills_education")
    private String requiredExperienceSkillsEducation;

    @Column(name = "offer")
    private String offer;

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
}
