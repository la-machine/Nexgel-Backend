package com.example.demo.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    EvaluationRepository evaluationRepository;


    //create
    public Evaluation createEvaluation(Evaluation evaluation){
        return evaluationRepository.save(evaluation);
    }

    //read
    public List<Evaluation> getEvaluation(){
        return evaluationRepository.findAll();
    }

    //delete
    public void deleteEvaluation(Long id){
        evaluationRepository.deleteById(id);
    }
    //Update
    public Evaluation updateEvaluation(long id, Evaluation evaluation){
        Evaluation eval= evaluationRepository.findById(id).get();
        eval.setEvaldate(evaluation.getEvaldate());
        return evaluationRepository.save(eval);
    }


}
