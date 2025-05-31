package com.henil.question_service.controllers;

import com.henil.question_service.models.Question;
import com.henil.question_service.models.QuestionWrapper;
import com.henil.question_service.models.Response;
import com.henil.question_service.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @GetMapping("allquestion")
    public List<Question> getAllQuestion(){
        return service.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return service.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return new ResponseEntity<>(service.addQuestion(question), HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> generate(@RequestParam String category, @RequestParam int numQuestions){
        return new ResponseEntity<>(service.generate(category, numQuestions), HttpStatus.OK);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIds){
        return new ResponseEntity<>(service.getQuestions(questionIds), HttpStatus.OK);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return new ResponseEntity<>(service.getScore(responses), HttpStatus.OK);
    }
}
