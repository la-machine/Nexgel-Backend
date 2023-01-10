package com.example.demo.evaluation;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate evaldate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEvaldate() {
        return evaldate;
    }

    public void setEvaldate(LocalDate evaldate) {
        this.evaldate = evaldate;
    }
}
