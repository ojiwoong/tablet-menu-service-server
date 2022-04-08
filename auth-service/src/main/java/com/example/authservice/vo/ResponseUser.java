package com.example.authservice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUser {
    private Long id;
    private String loginId;
    private String name;
    private String phoneNumber;
}
