package com.henil.quizService.services;

import com.henil.quizService.Feign.QuizInterface;
import com.henil.quizService.Repos.QuizRep;
import com.henil.quizService.models.Question;
import com.henil.quizService.models.QuestionWrapper;
import com.henil.quizService.models.Quiz;
import com.henil.quizService.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRep quizRepo;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title){
        List<Integer> questionIds = quizInterface.generate(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setQuestionIds(questionIds);
        quiz.setTitle(title);
        quizRepo.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id){
        Quiz quiz = quizRepo.findById(id).get();
        return quizInterface.getQuestions(quiz.getQuestionIds());
    }

    public ResponseEntity<Integer> getScoreById(int id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }
}
