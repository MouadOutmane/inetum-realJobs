package world.inetum.realdolmen.realjobs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import world.inetum.realdolmen.realjobs.entities.*;
import world.inetum.realdolmen.realjobs.entities.enums.Role;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.ResetPasswordExceptionMessage;
import world.inetum.realdolmen.realjobs.exceptions.messages.SignUpExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.security.ForgotRequest;
import world.inetum.realdolmen.realjobs.payload.security.ResetRequest;
import world.inetum.realdolmen.realjobs.payload.security.SignupRequest;
import world.inetum.realdolmen.realjobs.repositories.AccountRepository;
import world.inetum.realdolmen.realjobs.repositories.CountryRepository;
import world.inetum.realdolmen.realjobs.repositories.ResetCodeRepository;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.UUID;

import java.util.Optional;

@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private final PasswordEncoder encoder;
    private final CountryRepository countryRepository;
    private final AccountRepository accountRepository;
    private final MailService mailService;
    private final ResetCodeRepository resetCodeRepository;

    @Autowired
    public AccountService(PasswordEncoder encoder,
                          CountryRepository countryRepository,
                          AccountRepository accountRepository,
                          MailService mailService, ResetCodeRepository resetCodeRepository) {
        this.encoder = encoder;
        this.countryRepository = countryRepository;
        this.accountRepository = accountRepository;
        this.mailService = mailService;
        this.resetCodeRepository = resetCodeRepository;
    }

    private void saveCode(UUID code, Account account) {
        ResetCode resetCode = new ResetCode();
        resetCode.setCode(code);
        resetCode.setAccount(account);
        resetCodeRepository.save(resetCode);
    }

    @Transactional
    public void forgotPassword(ForgotRequest forgotRequest) throws MessagingException {
        Account account = accountRepository.findByEmail(forgotRequest.getEmail())
                .orElseThrow(() -> new EndpointException(HttpStatus.NOT_FOUND, ResetPasswordExceptionMessage.EMAIL_NOT_CORRECT));

        UUID code;
        do {
            code = UUID.randomUUID();
        } while (resetCodeRepository.existsById(code));

        saveCode(code, account);

        mailService.sendResetPasswordLink(account.getEmail(), code.toString());

        LOGGER.debug("{} requested a password reset! -> {}", forgotRequest.getEmail(), code);
    }

    @Transactional
    public void resetPassword(ResetRequest resetRequest) {
        ResetCode resetCode = resetCodeRepository.findById(resetRequest.getCode())
                .orElseThrow(() -> new EndpointException(HttpStatus.NOT_FOUND, ResetPasswordExceptionMessage.INVALID_CODE));

        if (!resetCode.isValid(LocalDateTime.now())) {
            resetCodeRepository.delete(resetCode);
            throw new EndpointException(HttpStatus.NOT_FOUND, ResetPasswordExceptionMessage.INVALID_CODE);
        }

        Account account = resetCode.getAccount();
        account.setPassword(encoder.encode(resetRequest.getPassword()));
        accountRepository.save(account);

        resetCodeRepository.deleteAllByAccountId(account.getId());

        LOGGER.debug("Reset password with for {} with the code {}", account.getEmail(), resetRequest.getCode().toString());
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
