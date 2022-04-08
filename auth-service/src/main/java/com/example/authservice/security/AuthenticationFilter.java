package com.example.authservice.security;

import com.example.authservice.dto.TokenDto;
import com.example.authservice.dto.UserDto;
import com.example.authservice.service.UserService;
import com.example.authservice.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private UserService userService;
    private Environment env;

    @Autowired
    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.userService = userService;
        this.env = env;
    }


    // POST:/login 시에 인증처리 시작 RequestLogin vo에 매핑되는 loginId, password 설정
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getLoginId(),
                    creds.getPassword(),
                    new ArrayList<>())
            );
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }



    // 인증 성공시 JWT 토근 발급
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        String userName = ((org.springframework.security.core.userdetails.User)authResult.getPrincipal()).getUsername();
        UserDto userDetails = userService.getUserByLoginId(userName);

        String token = Jwts.builder()
                        .setSubject(userDetails.getId().toString())
                        .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
                        .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret").getBytes())
                        .compact();

        TokenDto tokenDto = new TokenDto(token, userDetails.getId().toString());

        response.getWriter().write(new ObjectMapper().writeValueAsString(tokenDto));
    }
}
