package com.example.authservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String phoneNumber;
    private String role;
}
