package world.inetum.realdolmen.inetumrealJobs.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(	name = "users")
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "username", unique = true)
    private String username;
    @NotBlank
    @Column(name = "password")
    private String password;
    @NotBlank
    @Column(name = "gender")
    private String gender;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Past
    @Column(name = "birth_date")
    private Date dateOfBirth;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    @Column(name = "street_name")
    private String streetName;
    @NotBlank
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "box")
    private String box;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]+$")
    @Column(name = "city")
    private String city;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "country")
    @Pattern(regexp = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    @NotBlank
    private String country;
    @Size(min = 12, max = 12)
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(name ="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String user, String pword) {
        this.username=user;
        this.password=pword;
    }
}
