package com.app.forumchat.repos;

import com.app.forumchat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public User findByUsername(String username);
}
