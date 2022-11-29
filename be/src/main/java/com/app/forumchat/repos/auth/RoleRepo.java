package com.app.forumchat.repos.auth;

import com.app.forumchat.models.auth.ERole;
import com.app.forumchat.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(ERole name);

}
