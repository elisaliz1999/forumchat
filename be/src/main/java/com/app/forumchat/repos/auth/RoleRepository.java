package com.app.forumchat.repos.auth;


import com.app.forumchat.models.auth.ERole;
import com.app.forumchat.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(ERole name);
}
