package com.jmSpringBoot.crud.controller;

import com.jmSpringBoot.crud.dto.DtoResponseUser;
import com.jmSpringBoot.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    private final String restServerUrlLogin;
    private final String startWord;


    @Autowired
    public AuthController(@Value("${server.rest.login.url}")String restServerUrlLogin,
                          @Value("${jwt.token.secret.start.word}")String startWord) {
        this.restServerUrlLogin = restServerUrlLogin;
        this.startWord = startWord;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Login() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String getToken(User user, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        HttpSession session = request.getSession();
        try {
            ResponseEntity<DtoResponseUser> result = restTemplate.exchange(restServerUrlLogin, HttpMethod.POST, entity, DtoResponseUser.class);
            DtoResponseUser userDTOFromBody = result.getBody();
            String tokenCode = userDTOFromBody.getToken();
            userDTOFromBody.setToken(startWord + tokenCode);
            session.setAttribute("login", userDTOFromBody.getLogin());
            session.setAttribute("token",userDTOFromBody.getToken());
            session.setAttribute("roles",userDTOFromBody.getRoles());
            if(userDTOFromBody.getRoles().contains("ADMIN")){
                return "redirect:/admin";
            } else {
                return "redirect:/user";
            }
        } catch (HttpClientErrorException e) {
            System.out.println("ОШИБКА!!!");
            return "redirect:/error";
        }
    }
}
