package com.finalProject.FinalProject.dto;


import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String userFirstName;
    private String userLastName;

    private Set<String> roles = new HashSet<>();

}
