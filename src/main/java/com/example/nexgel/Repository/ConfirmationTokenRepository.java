package com.example.nexgel.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nexgel.model.token.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{
    
    Optional<ConfirmationToken> findByToken(String token);
    // @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmDate = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedDate(String token, LocalDateTime confirmedAt);

}
