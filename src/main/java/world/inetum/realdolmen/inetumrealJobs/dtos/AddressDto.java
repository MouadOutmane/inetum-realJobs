package world.inetum.realdolmen.inetumrealJobs.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressDto {

    @NotBlank
    String streetName;
    @NotBlank
    String houseNumber;
    String box;
    @NotBlank
    String city;
    @NotBlank
    String postalCode;
    @NotNull
    Long country;

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

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
