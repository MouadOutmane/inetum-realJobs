package world.inetum.realdolmen.inetumrealJobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.inetumrealJobs.payload.request.LoginRequest;
import world.inetum.realdolmen.inetumrealJobs.payload.request.SignupRequest;
import world.inetum.realdolmen.inetumrealJobs.payload.response.JwtResponse;
import world.inetum.realdolmen.inetumrealJobs.payload.response.MessageResponse;
import world.inetum.realdolmen.inetumrealJobs.repositories.AccountRepository;
import world.inetum.realdolmen.inetumrealJobs.security.jwt.JwtUtils;
import world.inetum.realdolmen.inetumrealJobs.services.AccountService;
import world.inetum.realdolmen.inetumrealJobs.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    PasswordEncoder encoder,
                                    JwtUtils jwtUtils,
                                    AccountRepository accountRepository,
                                    AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.accountRepository = accountRepository;
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
