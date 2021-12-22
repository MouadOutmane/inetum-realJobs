package world.inetum.realdolmen.realjobs.payload.query;

public class VacancyFilter {

    String functionTitle;
    String contractType;
    String country;
    String industry;
    Integer requiredYearsOfExperience;

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

    public Integer getRequiredYearsOfExperience() {
        return requiredYearsOfExperience;
    }

    public void setRequiredYearsOfExperience(Integer requiredYearsOfExperience) {
        this.requiredYearsOfExperience = requiredYearsOfExperience;
    }

}
