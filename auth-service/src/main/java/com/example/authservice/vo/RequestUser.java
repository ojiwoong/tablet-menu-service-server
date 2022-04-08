package com.example.authservice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUser {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String phoneNumber;
}
