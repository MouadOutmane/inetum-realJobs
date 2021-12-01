package world.inetum.realdolmen.inetumrealJobs.payload.request;

import lombok.Builder;
import lombok.Getter;
import world.inetum.realdolmen.inetumrealJobs.entities.ERole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Getter
public class SignupRequest {

    private String username;
    @NotNull
    private ERole role;
    @NotBlank
    private String gender;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private Date dateOfBirth;
    @NotBlank
    private String streetName;
    @NotBlank
    private String houseNumber;
    private String box;
    @NotBlank
    private String city;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String country;
    @NotBlank
    private String mobilePhone;
    private String profilePicture;
    @NotBlank
    private String password;

}
