package com.project.bime.controller;

import com.project.bime.exception.NotFoundException;
import com.project.bime.model.user.Role;
import com.project.bime.model.user.RoleName;
import com.project.bime.model.user.User;
import com.project.bime.payload.ApiResponse;
import com.project.bime.payload.JwtAuthenticationResponse;
import com.project.bime.payload.LoginRequest;
import com.project.bime.payload.SignUpRequest;
import com.project.bime.repository.RoleRepository;
import com.project.bime.repository.UserRepository;
import com.project.bime.security.JwtTokenProvider;
import com.project.bime.security.UserDetailsImpl;
import com.project.bime.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository,
                          UserDetailsServiceImpl userDetailsService
                          ) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticationUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateJwtToken(authentication);
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities()
                .stream().map(role -> role.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,
                userPrincipal.getId(),
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST
            );
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Email is already in use!"), HttpStatus.BAD_REQUEST
            );
        }

        // Create new user account
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new NotFoundException("User role not set!"));
        user.getRoles().add(userRole);
        User result = userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User " + result.getUsername() + " registered successfully!"));
    }
}
