package com.example.authservice.vo;

import lombok.Data;

@Data
public class RequestLogin {
    private String loginId;
    private String password;
}
