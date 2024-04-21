package com.finalProject.FinalProject.controller;



import com.finalProject.FinalProject.dto.MessageResponse;
import com.finalProject.FinalProject.dto.UserDto;
import com.finalProject.FinalProject.entity.User;
import com.finalProject.FinalProject.repository.RoleRepository;
import com.finalProject.FinalProject.repository.UserRepository;
import com.finalProject.FinalProject.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "userName") final String userName) {
        return ResponseEntity.ok(userService.get(userName));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody final UserDto userDto)
            throws MethodArgumentNotValidException {
        if (userService.userNameExists(userDto.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.emailExists(userDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
//        User user = new User(userDto.getUsername(),
//                userDto.getEmail(),
//                encoder.encode(userDto.getPassword()),
//                userDto.getUserFirstName(),
//                userDto.getUserLastName());
        userDto.setRoles(Collections.singleton("USER"));
        try {
            userService.create(userDto);
//            userRepository.save(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("one of role01s not found"));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<Void> updateUser(@PathVariable(name = "userName") final String userName,
            @RequestBody @Valid final UserDto userDTO) {
        userService.update(userName, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userName}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userName") final String userName) {
        userService.delete(userName);
        return ResponseEntity.noContent().build();
    }

}
