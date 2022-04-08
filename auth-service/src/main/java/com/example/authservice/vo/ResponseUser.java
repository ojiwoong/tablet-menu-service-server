package com.example.authservice.vo;

import lombok.Data;

@Data
public class ResponseUser {
    private Long id;
    private String loginId;
    private String name;
    private String phoneNumber;
}
