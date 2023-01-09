package com.example.nexgel.model.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.nexgel.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ConfirmationToken")
public class ConfirmationToken {

    @SequenceGenerator(
        name = "confirmation_token_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "confirmation_token_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdDate;
    @Column(nullable = false)
    private LocalDateTime expireDate;
    private LocalDateTime confirmDate;

    @ManyToOne
    @JoinColumn(
        nullable = false,
        name = "User_id"
    )
    private User user;

    public ConfirmationToken( String token, LocalDateTime createdDate, LocalDateTime expireDate,
            User user) {
        this.token = token;
        this.createdDate = createdDate;
        this.expireDate = expireDate;
        this.user = user;
    }

    
}
