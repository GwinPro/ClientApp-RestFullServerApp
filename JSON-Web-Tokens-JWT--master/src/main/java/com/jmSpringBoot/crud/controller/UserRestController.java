package com.jmSpringBoot.crud.controller;

import com.jmSpringBoot.crud.dto.DtoUserForClient;
import com.jmSpringBoot.crud.model.User;
import com.jmSpringBoot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/user/")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DtoUserForClient> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.getUserById(id);
        DtoUserForClient dtoUserForClient=new DtoUserForClient();
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(DtoUserForClient.getDtoUserForClient(user), HttpStatus.OK);
    }

}
