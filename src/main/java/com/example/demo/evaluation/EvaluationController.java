package com.example.demo.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EvaluationController{

    @Autowired
    EvaluationService evaluationService;

    @RequestMapping(value = "/evaluation",method = RequestMethod.POST)
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation){
        return evaluationService.createEvaluation(evaluation);
    }
    @RequestMapping(value="/evaluation/{eval_id}", method=RequestMethod.PUT)
    public Evaluation readEvaluation(@PathVariable(value = "eval_id") Long id, @RequestBody Evaluation evaluation) {
        return evaluationService.updateEvaluation(id, evaluation);
    }

    @RequestMapping(value="/evaluation/{eval_id}", method=RequestMethod.DELETE)
    public void deleteEvaluation(@PathVariable(value = "eval_id") Long id) {
        evaluationService.deleteEvaluation(id);
    }
}
