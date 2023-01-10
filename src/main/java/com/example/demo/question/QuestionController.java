package com.example.demo.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(path = "question/allquestions")
    public List<Question> getCourses(){
        List<Question> getAll=questionRepository.findAll();
        return getAll;
    }
    @RequestMapping(value = "/questions",method = RequestMethod.POST)
    public Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }
    @RequestMapping(value="/questions/{ques_id}", method=RequestMethod.PUT)
    public Question readQuestion(@PathVariable(value = "ques_id") Long id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    @RequestMapping(value="/questions/{ques_id}", method=RequestMethod.DELETE)
    public void deleteQuestion(@PathVariable(value = "ques_id") Long id) {
        questionService.deleteQuestion(id);
    }
}
