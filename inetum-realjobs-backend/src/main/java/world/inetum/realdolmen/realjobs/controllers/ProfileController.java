package world.inetum.realdolmen.realjobs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.controllers.annotations.RecruiterAndSeekerAllowed;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.ProfileExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.dtos.AccountReadDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ProfileMapper;
import world.inetum.realdolmen.realjobs.security.SecurityService;
import world.inetum.realdolmen.realjobs.services.AccountService;

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
    @RecruiterAndSeekerAllowed
    public AccountReadDto getPersonalInfo() {
        return profileMapper.toDto(securityService
                .getCurrentUser());
    }


}
