package world.inetum.realdolmen.inetumrealJobs.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import world.inetum.realdolmen.inetumrealJobs.entities.Role;

import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class SignupRequest {

    private String username;
    @NotBlank
    @Size(min = 8, max = 12)
    private String password;
    @NotBlank
    private String gender;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    private String lastName;
    @NotNull
    @Past
    private Date dateOfBirth;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    private String streetName;
    @NotBlank
    private String houseNumber;
    private String box;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    private String city;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    private String postalCode;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    private String country;
    @NotBlank
    @Size(min = 12, max = 12)
    private String mobilePhone;
    private String profilePicture;
    @NotNull
    private Role role;

}
