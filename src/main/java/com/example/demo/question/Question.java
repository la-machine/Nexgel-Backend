package com.example.demo.question;

import com.example.demo.course.Course;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ques_id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String correctAnswer;
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;
    public long getQues_id() {
        return ques_id;
    }

    public void setQues_id(long ques_id) {
        this.ques_id = ques_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


}
