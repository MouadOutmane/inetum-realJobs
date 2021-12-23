package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.payload.security.JwtResponse;
import world.inetum.realdolmen.realjobs.payload.security.LoginRequest;
import world.inetum.realdolmen.realjobs.payload.security.MessageResponse;
import world.inetum.realdolmen.realjobs.payload.security.SignupRequest;
import world.inetum.realdolmen.realjobs.security.UserDetailsImpl;
import world.inetum.realdolmen.realjobs.security.jwt.JwtUtils;
import world.inetum.realdolmen.realjobs.services.AccountService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AccountService accountService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtUtils jwtUtils,
                                    AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles);
    }

    @PostMapping("/signUp")
    public MessageResponse signUp(@Valid @RequestBody SignupRequest signUpRequest) {
        accountService.saveAccount(signUpRequest);

        return new MessageResponse("User registered successfully!");
    }
}
