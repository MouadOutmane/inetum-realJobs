package world.inetum.realdolmen.inetumrealJobs.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

    @NotBlank
    @Column(name = "street_name", nullable = false)
    private String streetName;

    // TODO: 14-Dec-21 ???
    @NotBlank
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "box")
    private String box;

    @NotBlank
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @NotNull
    @JoinColumn(
            name = "country_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String toString(){
        return getStreetName() + " " + getHouseNumber() + " " + getBox() + " " + getCity() + " " + getPostalCode() + " " + getCountry().getName();
    }
}
