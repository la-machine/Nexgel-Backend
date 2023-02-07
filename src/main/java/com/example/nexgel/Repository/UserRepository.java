package com.example.nexgel.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.nexgel.model.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmail(String email);
    // @Transactional
    // @Modifying
    // // @Query("UPDATE users u " +
    // //         "SET u.enabled = TRUE WHERE u.email = ?1")
    // // int enableUser(String email);
}
