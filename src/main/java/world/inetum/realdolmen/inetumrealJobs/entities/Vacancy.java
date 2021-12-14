package world.inetum.realdolmen.inetumrealJobs.entities;

import world.inetum.realdolmen.inetumrealJobs.entities.enums.ContractType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "vacancy")
public class Vacancy extends BaseModel {

    @NotBlank
    @Column(name = "function_title", nullable = false)
    private String functionTitle;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "contract_type", nullable = false)
    private ContractType contractType;

    @NotBlank
    @Column(name = "function_description", nullable = false)
    private String functionDescription;

    // TODO: 14-Dec-21 use posting company? Could be because of interim companies
//    @Column(name = "company_name")
//    private String companyName;
//
//    @Column(name = "industry")
//    private String industry;

    @JoinColumn(
            name = "company_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    @PositiveOrZero
    @Column(name = "required_years_of_experience", nullable = false)
    private Integer requiredYearsOfExperience;

    // TODO: 14-Dec-21 what
    @Column(name = "required_experience_skills_education")
    private String requiredExperienceSkillsEducation;

    @NotBlank
    @Column(name = "offer", nullable = false)
    private String offer;

    @JoinColumn(
            name = "recruiter_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Recruiter recruiter;

    public String getFunctionTitle() {
        return functionTitle;
    }

    public void setFunctionTitle(String functionTitle) {
        this.functionTitle = functionTitle;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
}
