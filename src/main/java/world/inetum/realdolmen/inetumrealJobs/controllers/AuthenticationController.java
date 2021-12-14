package world.inetum.realdolmen.inetumrealJobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.inetumrealJobs.entities.User;
import world.inetum.realdolmen.inetumrealJobs.payload.request.LoginRequest;
import world.inetum.realdolmen.inetumrealJobs.payload.request.SignupRequest;
import world.inetum.realdolmen.inetumrealJobs.payload.response.JwtResponse;
import world.inetum.realdolmen.inetumrealJobs.payload.response.MessageResponse;
import world.inetum.realdolmen.inetumrealJobs.repositories.UserRepository;
import world.inetum.realdolmen.inetumrealJobs.security.jwt.JwtUtils;
import world.inetum.realdolmen.inetumrealJobs.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .gender(signUpRequest.getGender())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .dateOfBirth(signUpRequest.getDateOfBirth())
                .streetName(signUpRequest.getStreetName())
                .houseNumber(signUpRequest.getHouseNumber())
                .box(signUpRequest.getBox())
                .city(signUpRequest.getCity())
                .postalCode(signUpRequest.getPostalCode())
                .country(signUpRequest.getCountry())
                .mobilePhone(signUpRequest.getMobilePhone())
                .profilePicture(signUpRequest.getProfilePicture())
                .role(signUpRequest.getRole())
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
