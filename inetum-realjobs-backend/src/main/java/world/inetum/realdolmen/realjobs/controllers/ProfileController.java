package world.inetum.realdolmen.realjobs.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.ProfileExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.dtos.AccountReadDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ProfileMapper;
import world.inetum.realdolmen.realjobs.security.SecurityService;
import world.inetum.realdolmen.realjobs.services.AccountService;

import javax.annotation.security.RolesAllowed;
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


    @GetMapping("/")
    @RolesAllowed({"JOB_SEEKER", "RECRUITER"})
    public AccountReadDto getPersonalInfo() {
        Account account = accountService
                .getPersonalInfo(securityService
                        .getCurrentUser()
                        .getEmail())
                .orElseThrow(() -> new EndpointException(ProfileExceptionMessage.PROFILE_NOT_FOUND));
        return profileMapper.toDto(account);
    }


}
