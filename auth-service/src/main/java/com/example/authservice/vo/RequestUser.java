package com.example.authservice.vo;

import lombok.Data;

@Data
public class RequestUser {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String phoneNumber;
}
