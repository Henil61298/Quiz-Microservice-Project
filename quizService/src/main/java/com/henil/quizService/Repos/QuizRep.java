package com.henil.quizService.Repos;

import com.henil.quizService.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRep extends JpaRepository<Quiz, Integer> {

}
