package world.inetum.realdolmen.inetumrealJobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.inetumrealJobs.entities.Account;
import world.inetum.realdolmen.inetumrealJobs.entities.Address;
import world.inetum.realdolmen.inetumrealJobs.entities.JobSeeker;
import world.inetum.realdolmen.inetumrealJobs.entities.Recruiter;
import world.inetum.realdolmen.inetumrealJobs.entities.enums.Role;
import world.inetum.realdolmen.inetumrealJobs.exceptions.EndpointException;
import world.inetum.realdolmen.inetumrealJobs.exceptions.messages.SignUpExceptionMessage;
import world.inetum.realdolmen.inetumrealJobs.payload.request.SignupRequest;
import world.inetum.realdolmen.inetumrealJobs.repositories.AccountRepository;
import world.inetum.realdolmen.inetumrealJobs.repositories.CountryRepository;

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
            String strRole = signUpRequest.getRole().toString();
            if (strRole.equals(Role.JOB_SEEKER.toString())) {
                JobSeeker jobSeeker = new JobSeeker();
                buildAccount(signUpRequest, jobSeeker);
                accountRepository.save(jobSeeker);
            } else if (strRole.equals(Role.RECRUITER.toString())) {
                Recruiter recruiter = new Recruiter();
                buildAccount(signUpRequest, recruiter);
                accountRepository.save(recruiter);
            }else {
                throw new IllegalArgumentException("Non existing role");
            }
        }
    }

    private void buildAccount(SignupRequest signUpRequest, Account account) {
        Address address = Address.builder()
                .streetName(signUpRequest.getAddress().getStreetName())
                .houseNumber(signUpRequest.getAddress().getHouseNumber())
                .box(signUpRequest.getAddress().getBox())
                .city(signUpRequest.getAddress().getCity())
                .postalCode(signUpRequest.getAddress().getPostalCode())
                .country(countryRepository.getById(signUpRequest.getAddress().getCountry()))
                .build();

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
