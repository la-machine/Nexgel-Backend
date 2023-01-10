package com.example.demo.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    //create
    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    //read
    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    //delete
    public void deleteQuestion(Long ques_id){
        questionRepository.deleteById(ques_id);
    }
    //Update
    public Question updateQuestion(long ques_id, Question question){
        Question ques= questionRepository.findById(ques_id).get();
        ques.setQuestion(question.getQuestion());
        ques.setAnswer1(question.getAnswer1());
        ques.setAnswer2(question.getAnswer2());
        ques.setAnswer3(question.getAnswer3());
        ques.setCorrectAnswer(question.getCorrectAnswer());
        ques.setDuration(question.getDuration());
        return questionRepository.save(ques);
    }


}
