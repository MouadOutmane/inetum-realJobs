package world.inetum.realdolmen.realjobs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.controllers.annotations.JobSeekerAndRecruiter;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.ProfileExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.dtos.ProfileDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ProfileMapper;
import world.inetum.realdolmen.realjobs.security.SecurityService;
import world.inetum.realdolmen.realjobs.services.AccountService;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final AccountService accountService;
    private final ProfileMapper profileMapper;
    private final SecurityService securityService;

    @Autowired
    public ProfileController(AccountService accountService, ProfileMapper profileMapper, SecurityService securityService) {
        this.accountService = accountService;
        this.profileMapper = profileMapper;
        this.securityService = securityService;
    }


    @GetMapping("/{email}")
    // TODO roles should be included in the jwt so the preAuthorize can work
    //  @JobSeekerAndRecruiter
    public ResponseEntity<?> getPersonalInfo(@PathVariable(value = "email") String email) {
        // TODO getCurrentUser does not work
        //validateAuthorization(email);
        Optional<Account> accountPersonalInfo = this.accountService.getPersonalInfo(email);
        if (accountPersonalInfo.isPresent()) {
            ProfileDto profileDto = profileMapper.toDto(accountPersonalInfo.get());
            return new ResponseEntity(profileDto, HttpStatus.OK);
        }
        throw new EndpointException(ProfileExceptionMessage.PROFILE_NOT_FOUND);
    }


    private void validateAuthorization(String email) {
        if (!securityService.getCurrentUser().getEmail().equals(email)) {
            throw new EndpointException(ProfileExceptionMessage.UNAUTHORIZED_REQUEST);
        }
    }


}
