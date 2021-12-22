package world.inetum.realdolmen.realjobs.entities;

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
    @JoinColumn(
            name = "country_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @NotBlank
    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "logo")
    private String logo;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String companyLogo) {
        this.logo = companyLogo;
    }
}
