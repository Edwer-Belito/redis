package com.nttdata.controller;


import com.nttdata.service.UserService;
import com.nttdata.model.User;
import lombok.AllArgsConstructor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/redis")
@AllArgsConstructor
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping("/save")
    public User save(@RequestBody User user) {
    	return userService.saveUser(user);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
    	return ResponseEntity.ok(userService.getAllUser());
    }
    
    @GetMapping("/getOne/{id}")
    public User getOne(@PathVariable Long id) {
    	return userService.getOneUser(id);
    }
    
    @PutMapping("/modify/{id}")
    public User update (@RequestBody User user,@PathVariable Long id) {
    	return userService.updateUser(user, id);
    }
    

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
    	userService.deleteUser(id);
    	return "User deleted" + id + "";
    }
    
}
