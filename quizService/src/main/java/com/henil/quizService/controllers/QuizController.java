package com.henil.quizService.controllers;


import com.henil.quizService.models.QuizDTO;
import com.henil.quizService.models.Response;
import com.henil.quizService.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService service;

    @PostMapping("/create")
    private ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        return service.createQuiz(quizDTO.getCategory(), quizDTO.getNumQ(), quizDTO.getTitle());
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<?> getQuizQuestion(@PathVariable int id){
        return service.getQuizQuestion(id);
    }

    @PostMapping("/score/{id}")
    private ResponseEntity<?> getScoreById(@PathVariable int id, @RequestBody List<Response> responses){
        return service.getScoreById(id, responses);
    }
}
