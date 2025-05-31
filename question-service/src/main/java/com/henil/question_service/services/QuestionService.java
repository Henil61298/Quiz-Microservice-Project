package com.henil.question_service.services;

import com.henil.question_service.Repos.QuestionRep;
import com.henil.question_service.models.Question;
import com.henil.question_service.models.QuestionWrapper;
import com.henil.question_service.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRep repo;

    public List<Question> getAllQuestion(){
        return repo.findAll();
    }

    public List<Question> getQuestionsByCategory(String category){
        return repo.findByCategory(category);
    }

    public String addQuestion(Question question){
        repo.save(question);
        return "success";
    }

    public List<Integer> generate(String category, int numQuestions) {
        return repo.findRandomQuestionByCategory(category, numQuestions);
    }

    public List<QuestionWrapper> getQuestions(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();

        for (Integer i : questionIds){
            Question question = repo.findById(i).get();
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestion_title(question.getQuestion_title());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return wrappers;
    }

    public Integer getScore(List<Response> responses) {
        int score = 0;
        for (Response response : responses){
            Question question = repo.findById(response.getId()).get();
            String correctAnswer = question.getRightAnswer();
            if (response.getAnswer().equals(correctAnswer)){
                score++;
            }
        }

        return score;
    }
}
