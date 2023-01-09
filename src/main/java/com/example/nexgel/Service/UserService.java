package com.example.nexgel.Service;


import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nexgel.Repository.UserRepository;
import com.example.nexgel.model.User;
import com.example.nexgel.model.token.ConfirmationToken;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        return userRepository.findByEmail(email);
    }

    public String signUpUser(User user){
        User userExists = userRepository
            .findByEmail(user.getEmail());

        if(userExists != null){
            throw new IllegalStateException("email already exist");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmToken = new ConfirmationToken(
            token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user
        );
        tokenService.saveConfirmationToken(confirmToken);

        return token;
    } 
    
    public int enableUser(String email) {
        User perUser = userRepository.findByEmail(email);
        if(perUser == null){
            throw new IllegalStateException("User not found");
        }
        perUser.setEnabled(true);
        userRepository.save(perUser);
        return 1;
    }
    
}
