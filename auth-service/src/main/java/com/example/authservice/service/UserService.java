package com.example.authservice.service;

import com.example.authservice.dto.UserDto;
import com.example.authservice.vo.RequestUser;

import java.util.List;

public interface UserService {
    
    /* 회원가입 */
    UserDto signup(UserDto userDto);

    /* 회원 전체 조회 */
    List<UserDto> getAllUsers();

    /* 회원 id 검색 */
    UserDto getUser(long id);

    /* 회원 로그인 아이디 검색 */
    UserDto getUserByLoginId(String loginId);

    /* 회원 수정 */
    UserDto updateUser(UserDto userDto);

    /* 회원 삭제 */
    void deleteUser(Long id);
}
