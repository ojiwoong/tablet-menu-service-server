package com.example.authservice.service;

import com.example.authservice.entity.UserEntity;
import com.example.authservice.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{
    UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLoginId(username);

        if(userEntity == null) throw new UsernameNotFoundException(username);

        return new User(userEntity.getLoginId(), userEntity.getPassword(), authorities(userEntity));
    }

    private static Collection<? extends GrantedAuthority> authorities(UserEntity userEntity) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userEntity.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }
}
