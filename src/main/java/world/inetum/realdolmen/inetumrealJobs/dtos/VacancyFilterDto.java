package world.inetum.realdolmen.inetumrealJobs.dtos;

public class VacancyFilterDto {
    String functionTitle;

    String contractType;

    String country;

    String industry;

    String requiredYearsOfExperience;

    public String getFunctionTitle() {
        return functionTitle;
    }

    public void setFunctionTitle(String functionTitle) {
        this.functionTitle = functionTitle;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getRequiredYearsOfExperience() {
        return requiredYearsOfExperience;
    }

    public void setRequiredYearsOfExperience(String requiredYearsOfExperience) {
        this.requiredYearsOfExperience = requiredYearsOfExperience;
    }
}
