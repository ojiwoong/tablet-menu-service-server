package com.example.authservice.controller;

import com.example.authservice.dto.UserDto;
import com.example.authservice.service.UserService;
import com.example.authservice.vo.RequestUser;
import com.example.authservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* 회원 가입 */
    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 엄격한 매칭

        UserDto userDto = mapper.map(requestUser, UserDto.class);

        userDto = userService.signup(userDto);

        ResponseUser result = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /* 전체 회원 조회 */
    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getAllUsers() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<UserDto> userDtoList = userService.getAllUsers();

        List<ResponseUser> responseUserList = new ArrayList<>();

        userDtoList.forEach(userDto -> {
            responseUserList.add(new ModelMapper().map(userDto, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responseUserList);
    }

    /* 회원 id 검색 */
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("id") Long id) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = userService.getUser(id);

        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    /* 회원 로그인 아이디 검색 */
    @GetMapping("/users/search")
    public ResponseEntity<ResponseUser> getUser(@RequestParam(value="loginId") String loginId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = userService.getUserByLoginId(loginId);

        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    /* 회원 수정 */
    @PutMapping("/users")
    public ResponseEntity<ResponseUser> updateUser(@RequestBody RequestUser requestUser) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestUser, UserDto.class);

        userDto = userService.updateUser(userDto);

        ResponseUser responseUser = mapper.map(requestUser, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    /* 회원 삭제 */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }
}
