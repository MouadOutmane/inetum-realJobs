package world.inetum.realdolmen.realjobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import world.inetum.realdolmen.realjobs.entities.*;
import world.inetum.realdolmen.realjobs.entities.enums.Gender;

import java.time.LocalDate;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue", "unused"})
public abstract class BaseIntegrationTest extends BaseRepositoryTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected Recruiter persistRecruiter(String email, String password, Company company) {
        Recruiter user = new Recruiter();
        user.setCompany(company);

        updateAccount(user, email, password);

        return accountRepository.save(user);
    }

    protected JobSeeker persistJobSeeker(String email, String password) {
        return persistJobSeeker(email, password, new Resume());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected JobSeeker persistJobSeeker(String email, String password, Resume resume) {
        JobSeeker user = new JobSeeker();
        user.setResume(resume);

        updateAccount(user, email, password);

        return accountRepository.save(user);
    }

    private <T extends Account> void updateAccount(T account, String email, String password) {
        Country country = persistCountry("country");

        Address address = new Address();
        address.setStreetName("street");
        address.setHouseNumber("number");
        address.setBox(null);
        address.setCity("city");
        address.setPostalCode("postal");
        address.setCountry(country);

        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        account.setGender(Gender.MALE);
        account.setFirstName("first name");
        account.setLastName("last name");
        account.setDateOfBirth(LocalDate.of(2020, 1, 1));
        account.setAddress(address);
        account.setMobilePhone(null);
        account.setProfilePicture("picture");
    }

}
