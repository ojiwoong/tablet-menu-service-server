package com.example.authservice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLogin {
    private String loginId;
    private String password;
}
