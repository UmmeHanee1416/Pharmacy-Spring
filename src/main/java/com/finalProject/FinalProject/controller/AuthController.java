package com.finalProject.FinalProject.controller;



import com.finalProject.FinalProject.dto.JwtResponse;
import com.finalProject.FinalProject.dto.LoginRequest;
import com.finalProject.FinalProject.dto.MessageResponse;
import com.finalProject.FinalProject.dto.UserDto;
import com.finalProject.FinalProject.entity.Role;
import com.finalProject.FinalProject.entity.User;
import com.finalProject.FinalProject.repository.RoleRepository;
import com.finalProject.FinalProject.repository.UserRepository;
import com.finalProject.FinalProject.security.jwt.JwtUtils;
import com.finalProject.FinalProject.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


//    List<String> roles = userDetails.getAuthorities().stream()
//        .map(item -> item.getAuthority())
//        .collect(Collectors.toList());

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

//    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//        .body(new UserInfoResponse(userDetails.getId(),
//                                   userDetails.getUsername(),
//                                   userDetails.getEmail(),
//                                   roles));



        String token = jwtUtils.generateJwtToken(userDetails);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new JwtResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        userDto.setRoles(Collections.singleton("USER"));
        // Create new user's account
        User user = new User(userDto.getUsername(),
                userDto.getEmail(),
                encoder.encode(userDto.getPassword()),
                userDto.getUserFirstName(),
                userDto.getUserLastName());

        Set<String> strRoles = userDto.getRoles();
//        Set<Role> roles = new HashSet<>();
        Set<Role> roles = roleRepository.findAllById(userDto.getRoles() == null ? Collections.emptyList() : userDto.getRoles()).stream().collect(Collectors.toSet());
        if (roles.size() != (userDto.getRoles() == null ? 0 : userDto.getRoles().size())) {
            throw new RuntimeException("one of role01s not found");
        }
        user.setRoles(new HashSet<>(roles));
//
//        if (strRoles != null) {
//            Role userRole = roleRepository.findById("USER")
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        }
//        else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "ADMIN":
//                        Role adminRole = roleRepository.findById("ADMIN")
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "MODERATOR":
//                        Role modRole = roleRepository.findById("MODERATOR")
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findById("USER")
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }

//        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
