package com.jmSpringBoot.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmSpringBoot.crud.model.Role;
import com.jmSpringBoot.crud.model.User;

import java.util.Objects;
import java.util.Set;

public class DtoUserForClient {

    private long id;

    private String email;

    private String login;

    private String password;

    private Set<Role> roles;

    public DtoUserForClient() {
    }

    public DtoUserForClient(long id, String email, String login, String password, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public DtoUserForClient(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DtoUserForClient that = (DtoUserForClient) o;
        return Objects.equals(email, that.email);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override

    public int hashCode() {

        return Objects.hash(email);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public static DtoUserForClient getDtoUserForClient (User user){
        DtoUserForClient dtoUserForClient = new DtoUserForClient();
        dtoUserForClient.setId(user.getId());
        dtoUserForClient.setEmail(user.getEmail());
        dtoUserForClient.setLogin(user.getLogin());
        dtoUserForClient.setPassword(user.getPassword());
        dtoUserForClient.setRoles(user.getRoles());
        return dtoUserForClient;
    }

    public static User getUserFromDtoUser (DtoUserForClient userForClient){
        User user = new User();
        user.setEmail(userForClient.getEmail());
        user.setLogin(userForClient.getLogin());
        user.setPassword(userForClient.getPassword());
        user.setRoles(userForClient.getRoles());
        return user;
    }

}
