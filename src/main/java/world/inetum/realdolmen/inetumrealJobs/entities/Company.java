package world.inetum.realdolmen.inetumrealJobs.entities;

import world.inetum.realdolmen.inetumrealJobs.entities.enums.Country;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Company")
public class Company extends BaseModel {

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false)
    private Country country;

    @NotBlank
    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "logo")
    private String companyLogo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }
}
