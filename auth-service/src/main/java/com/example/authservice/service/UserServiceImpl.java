package com.example.authservice.service;

import com.example.authservice.dto.UserDto;
import com.example.authservice.entity.UserEntity;
import com.example.authservice.jpa.UserRepository;
import com.example.authservice.vo.RequestUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public UserDto signup(UserDto userDto) {
        // userDto -> userEntity -> jpa -> mariaDB
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 엄격한 매칭
        UserEntity userEntity = mapper.map(userDto, UserEntity.class); // userDto -> userEntity 로 매핑

        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword())); // 비밀번호 암호화

        userEntity = userRepository.save(userEntity);

        UserDto user = mapper.map(userEntity, UserDto.class);

        return user;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        List<UserDto> userDtoList = new ArrayList<>();

        userEntities.forEach(v -> {
            userDtoList.add(new ModelMapper().map(v, UserDto.class));
        });

        return userDtoList;
    }

    @Override
    public UserDto getUser(long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto getUserByLoginId(String loginId) {
        UserEntity userEntity = userRepository.findByLoginId(loginId);

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        // 변경 가능 데이터 : password, phoneNumber,name, role
        Optional<UserEntity> optUserEntity = userRepository.findById(userDto.getId());

        UserEntity userEntity = optUserEntity.orElse(null);

        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setName(userDto.getName());
        userEntity.setRole(userDto.getRole());

        userEntity = userRepository.save(userEntity);

        userDto = new ModelMapper().map(userEntity, UserDto.class);

        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        UserDto userDto = getUser(id);

        UserEntity userEntity = new ModelMapper().map(userDto, UserEntity.class);

        userRepository.delete(userEntity);
    }
}
