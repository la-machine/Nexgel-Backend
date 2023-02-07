package com.example.nexgel.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.nexgel.Repository.ConfirmationTokenRepository;
import com.example.nexgel.model.token.ConfirmationToken;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    
    private final ConfirmationTokenRepository confirmRepo;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmRepo.save(token);
    }
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmRepo.findByToken(token);
    }

    public int setConfirmedDate(String token) {
        return confirmRepo.updateConfirmedDate(
                token, LocalDateTime.now());
    }
}
