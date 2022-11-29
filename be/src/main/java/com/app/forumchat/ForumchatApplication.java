package com.app.forumchat;

import com.app.forumchat.models.auth.ERole;
import com.app.forumchat.models.auth.Role;
import com.app.forumchat.repos.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumchatApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ForumchatApplication.class, args);
	}

	@Autowired private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.findByName(ERole.USER) == null) {
			Role roleUser = new Role(ERole.USER);
			roleRepository.save(roleUser);
		}
		if (roleRepository.findByName(ERole.ADMIN) == null) {
			Role roleAdmin = new Role(ERole.ADMIN);
			roleRepository.save(roleAdmin);
		}
	}
}
