package world.inetum.realdolmen.inetumrealJobs.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
        })
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "username")
    private String username;
    @NotBlank
    @Column(name = "password")
    private String password;
    @NotBlank
    @Column(name = "gender")
    private String gender;
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "birth_date")
    private Date dateOfBirth;
    @NotBlank
    @Column(name = "street_name")
    private String streetName;
    @NotBlank
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "box")
    private String box;
    @NotBlank
    @Column(name = "city")
    private String city;
    @NotBlank
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "country")
    @NotBlank
    private String country;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name ="role")
    @Enumerated(EnumType.STRING)
    private ERole role;

    public User() {
    }

    @Builder
    public User( String user, String password) {
        this.username = user;
        this.password = password;
    }

    public User(String username, String password, String gender, String firstName, String lastName,
                Date dateOfBirth, String streetName, String houseNumber, String box, String city, String postalCode,
                String country, String mobilePhone, String profilePicture, ERole role) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.box = box;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.mobilePhone = mobilePhone;
        this.profilePicture = profilePicture;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole roles) {
        this.role = roles;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNr() {
        return houseNumber;
    }

    public void setNr(String houseNumber) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
