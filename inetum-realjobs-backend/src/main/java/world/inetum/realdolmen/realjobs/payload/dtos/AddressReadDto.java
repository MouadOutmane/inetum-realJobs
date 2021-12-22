package world.inetum.realdolmen.realjobs.payload.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressReadDto {

    @NotBlank
    private String streetName;
    @NotBlank
    private String houseNumber;
    private String box;
    @NotBlank
    private String city;
    @NotBlank
    private String postalCode;
    @NotNull
    private Long country;

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
