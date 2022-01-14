package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.Address;
import world.inetum.realdolmen.realjobs.entities.Company;
import world.inetum.realdolmen.realjobs.entities.Recruiter;
import world.inetum.realdolmen.realjobs.entities.enums.ContractType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VacancyCreateDto {
    @NotBlank
    private String functionTitle;
    @NotNull
    private ContractType contractType;
    @NotBlank
    private String functionDescription;
    private Company company;
    @NotNull
    private Address address;
    @NotNull
    private Integer requiredYearsOfExperience;
    private String requirements;
    @NotBlank
    private String offer;
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

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
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
