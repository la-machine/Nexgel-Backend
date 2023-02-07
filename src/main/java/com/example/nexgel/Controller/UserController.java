package com.example.nexgel.Controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexgel.Config.LoginResponse;
import com.example.nexgel.Config.RegistrationRequest;
import com.example.nexgel.Config.RequestAuthentication;
import com.example.nexgel.Repository.UserRepository;
import com.example.nexgel.Service.RegistrationService;
import com.example.nexgel.Security.JWTTokenHelper;
import com.example.nexgel.model.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api")
@AllArgsConstructor
public class UserController {

    @Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
	JWTTokenHelper jWTTokenHelper;
    
    private final RegistrationService registrationService;
    
    private final UserRepository userRepository;
    
    @PostMapping(path = "/register")
    public String register (@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody RequestAuthentication authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user=(User)authentication.getPrincipal();
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());
		
		LoginResponse response=new LoginResponse();
		response.setToken(jwtToken);
		

		return ResponseEntity.ok(response);
	}

    @GetMapping(path = "/listuser")
    public List<User> GetAllUser(){
        return userRepository.findAll();
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
            updateUser.setBranch(userdetails.getBranch());
            updateUser.setEmail(userdetails.getEmail());
            updateUser.setPassword(userdetails.getPassword());
            userRepository.save(userdetails);
        }
        return ResponseEntity.ok(updateUser);
    }
}
