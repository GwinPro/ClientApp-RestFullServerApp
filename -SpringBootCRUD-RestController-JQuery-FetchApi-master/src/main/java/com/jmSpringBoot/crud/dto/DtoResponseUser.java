package com.jmSpringBoot.crud.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jmSpringBoot.crud.model.Role;

import java.util.Set;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoResponseUser {
    private String login;
    private String token;
    private String roles;


    public DtoResponseUser(String login, String token, String roles) {
        this.login = login;
        this.token = token;
        this.roles = roles;
    }

    public DtoResponseUser() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
