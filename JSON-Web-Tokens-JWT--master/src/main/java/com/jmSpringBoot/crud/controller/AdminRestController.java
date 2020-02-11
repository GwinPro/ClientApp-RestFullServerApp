package com.jmSpringBoot.crud.controller;

import com.jmSpringBoot.crud.dto.DtoUserForClient;
import com.jmSpringBoot.crud.model.Role;
import com.jmSpringBoot.crud.model.User;
import com.jmSpringBoot.crud.service.RoleService;
import com.jmSpringBoot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rest/admin/")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("users")
    public ResponseEntity<List<DtoUserForClient>> getAllUsers() {
        List<User> users = this.userService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DtoUserForClient> dtoUsersForClient = new ArrayList<>();
        for (User user : users) {
            dtoUsersForClient.add(DtoUserForClient.getDtoUserForClient(user));
        }

        return ResponseEntity.ok().body(dtoUsersForClient);
    }

    @PostMapping("addUser")
    public ResponseEntity<DtoUserForClient> createUser(@RequestBody DtoUserForClient dtoUser) {
        User userFromBase = userService.getUserByName(dtoUser.getEmail());
        if (userFromBase != null) {
            return ResponseEntity.notFound().build();
        }

        User user = DtoUserForClient.getUserFromDtoUser(dtoUser);
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<DtoUserForClient> deleteUser(@PathVariable(value = "id") Long id) {
        User userFromBase = userService.getUserById(id);
        if (userFromBase == null) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(userFromBase.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("updateUser/{id}")
    public ResponseEntity<DtoUserForClient> getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        DtoUserForClient dtoUserForClient = DtoUserForClient.getDtoUserForClient(user);
        return ResponseEntity.ok().body(dtoUserForClient);
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<DtoUserForClient> updateEmployee(@PathVariable(value = "id") Long userId, @Valid @RequestBody DtoUserForClient editUser) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setEmail(editUser.getEmail());
        user.setLogin(editUser.getLogin());
        user.setPassword(editUser.getPassword());
        user.setRoles(editUser.getRoles());
        userService.updateUser(user);
        return ResponseEntity.ok().body(DtoUserForClient.getDtoUserForClient(user));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DtoUserForClient> viewAdmin(@PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(DtoUserForClient.getDtoUserForClient(user), HttpStatus.OK);
    }

    @GetMapping("roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok().body(this.roleService.getRoles());
    }

    @GetMapping("roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long roleId) {
        Role role = roleService.getRoleById(roleId);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(role);
    }
}
