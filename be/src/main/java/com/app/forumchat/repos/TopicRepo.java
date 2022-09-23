package com.app.forumchat.repos;

import com.app.forumchat.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, Long> {
}
