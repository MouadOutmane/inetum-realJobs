package world.inetum.realdolmen.realjobs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.payload.dtos.ProfileDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ProfileMapper;
import world.inetum.realdolmen.realjobs.services.AccountService;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final AccountService accountService;
    private final ProfileMapper profileMapper;

    @Autowired
    public ProfileController(AccountService accountService, ProfileMapper profileMapper) {
        this.accountService = accountService;
        this.profileMapper = profileMapper;
    }


    @GetMapping("/{email}")
    public ResponseEntity<?> getPersonalInfo(@PathVariable(value = "email") String email) {
        // TODO check if logged in user id is the same as the id
        Optional<Account> accountPersonalInfo = this.accountService.getPersonalInfo(email);
        if (accountPersonalInfo.isPresent()) {
            ProfileDto profileDto = profileMapper.toDto(accountPersonalInfo.get());
            return new ResponseEntity(profileDto, HttpStatus.OK);
        }
        // TODO user not found
        throw new IllegalArgumentException();
    }

}
