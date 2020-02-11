package com.jmSpringBoot.crud.controller;

import com.jmSpringBoot.crud.dto.DtoUserForLogin;
import com.jmSpringBoot.crud.model.User;
import com.jmSpringBoot.crud.secure.jwt.JwtTokenProvider;
import com.jmSpringBoot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/rest/auth/")
public class AuthenticationRestController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationRestController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody User user) {
        try {
            String email = user.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,user.getPassword()));
            User authUser = userService.getUserByName(email);
            if (authUser == null) {
                throw new UsernameNotFoundException("User with username: " + email + " not found");            }
            String token = jwtTokenProvider.createToken(email, authUser.getRoles());
            DtoUserForLogin dtoUserForLogin = new DtoUserForLogin();
            dtoUserForLogin.setLogin(authUser.getLogin());
            dtoUserForLogin.setToken(token);
            dtoUserForLogin.setRoles(DtoUserForLogin.rolesToString(authUser.getRoles()));
            return ResponseEntity.ok().body(dtoUserForLogin);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }

    }
}
