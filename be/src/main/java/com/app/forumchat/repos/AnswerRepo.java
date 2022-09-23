package com.app.forumchat.repos;

import com.app.forumchat.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
}
