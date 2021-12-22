package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.entities.Address;
import world.inetum.realdolmen.realjobs.entities.JobSeeker;
import world.inetum.realdolmen.realjobs.entities.Recruiter;
import world.inetum.realdolmen.realjobs.entities.enums.Role;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.SignUpExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.security.SignupRequest;
import world.inetum.realdolmen.realjobs.repositories.AccountRepository;
import world.inetum.realdolmen.realjobs.repositories.CountryRepository;

@Service
public class AccountService {

    private final PasswordEncoder encoder;
    private final CountryRepository countryRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(PasswordEncoder encoder,
                          CountryRepository countryRepository,
                          AccountRepository accountRepository) {
        this.encoder = encoder;
        this.countryRepository = countryRepository;
        this.accountRepository = accountRepository;
    }

    public void saveAccount(SignupRequest signUpRequest) {
        if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EndpointException(SignUpExceptionMessage.EMAIL_ALREADY_USED);
        }

        if (countryRepository.existsById(signUpRequest.getAddress().getCountry())) {
            if (signUpRequest.getRole().equals(Role.JOB_SEEKER)) {
                JobSeeker jobSeeker = new JobSeeker();
                buildAccount(signUpRequest, jobSeeker);
                accountRepository.save(jobSeeker);
            } else if (signUpRequest.getRole().equals(Role.RECRUITER)) {
                Recruiter recruiter = new Recruiter();
                buildAccount(signUpRequest, recruiter);
                accountRepository.save(recruiter);
            }else {
                throw new IllegalArgumentException("Non existing role");
            }
        }
    }

    private void buildAccount(SignupRequest signUpRequest, Account account) {
        Address address = new Address(
                signUpRequest.getAddress().getStreetName(),
                signUpRequest.getAddress().getHouseNumber(),
                signUpRequest.getAddress().getBox(),
                signUpRequest.getAddress().getCity(),
                signUpRequest.getAddress().getPostalCode(),
                countryRepository.getById(signUpRequest.getAddress().getCountry())
        );

        account.setEmail(signUpRequest.getEmail());
        account.setPassword(encoder.encode(signUpRequest.getPassword()));
        account.setGender(signUpRequest.getGender());
        account.setFirstName(signUpRequest.getFirstName());
        account.setLastName(signUpRequest.getLastName());
        account.setDateOfBirth(signUpRequest.getBirthDate());
        account.setAddress(address);
        account.setMobilePhone(signUpRequest.getMobilePhone());
        account.setProfilePicture(signUpRequest.getProfilePicture());
    }
}
