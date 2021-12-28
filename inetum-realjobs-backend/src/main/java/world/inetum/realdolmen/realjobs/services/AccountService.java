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

import java.util.Optional;

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

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public void saveAccount(SignupRequest signUpRequest) {
        if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EndpointException(SignUpExceptionMessage.EMAIL_ALREADY_USED);
        }

        if (!countryRepository.existsById(signUpRequest.getAddress().getCountry())) {
            throw new EndpointException(SignUpExceptionMessage.INCORRECT_DATA);
        }

        Account account;
        if (signUpRequest.getRole().equals(Role.JOB_SEEKER)) {
            account = new JobSeeker();
        } else if (signUpRequest.getRole().equals(Role.RECRUITER)) {
            account = new Recruiter();
        } else {
            throw new EndpointException(SignUpExceptionMessage.INCORRECT_DATA);
        }

        buildAccount(signUpRequest, account);
        accountRepository.save(account);
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
