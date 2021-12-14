package world.inetum.realdolmen.inetumrealJobs.dtos;

import javax.validation.constraints.NotEmpty;


public class VacancyFilterResultDto {

    @NotEmpty(message = "Please provide a function title")
    private String functionTitle;

    @NotEmpty(message = "Please provide a contract type")
    private String contractType;

    @NotEmpty(message = "Please provide a city")
    private String city;

    @NotEmpty(message = "Please provide an offer")
    private String offer;

    @NotEmpty(message = "Please provide a company name")
    private String companyName;

    private String postingDate;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }
}
