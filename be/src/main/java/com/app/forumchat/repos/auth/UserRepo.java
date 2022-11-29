package com.app.forumchat.repos.auth;

import com.app.forumchat.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
