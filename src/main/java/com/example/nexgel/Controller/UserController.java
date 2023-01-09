package com.example.nexgel.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexgel.Config.RegistrationRequest;
import com.example.nexgel.Repository.UserRepository;
import com.example.nexgel.Service.RegistrationService;
import com.example.nexgel.model.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/register")
@AllArgsConstructor
public class UserController {

    private final RegistrationService registrationService;
    private final UserRepository userRepository;
    
    @PostMapping
    public String register (@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String Confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

    @PutMapping("{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User userdetails){
        User updateUser = userRepository.findByEmail(email);
        if(updateUser == null){
            throw new IllegalStateException("User not exist with id : " + email);
        }else{
            updateUser.setFirstname(userdetails.getUsername());
            updateUser.setLastname(userdetails.getLastname());
            updateUser.setPhonenumber(userdetails.getPhonenumber());
            updateUser.setEmail(userdetails.getEmail());
            updateUser.setPassword(userdetails.getPassword());
            userRepository.save(userdetails);
        }
        return ResponseEntity.ok(updateUser);
    }
}
