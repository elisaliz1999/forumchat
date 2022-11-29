package com.app.forumchat.services.auth;

import com.app.forumchat.models.auth.ERole;
import com.app.forumchat.models.auth.Role;
import com.app.forumchat.models.auth.User;
import com.app.forumchat.models.auth.UserDetailsModelDTO;
import com.app.forumchat.repos.auth.RoleRepository;
import com.app.forumchat.repos.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthUserService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    private PasswordEncoder encoder;

    public AuthUserService() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User Not Found with username: " + username);
            }
            System.out.println(user);
            return UserDetailsModelDTO.build(user);
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }

    /**
     * @return
     * @throws UsernameNotFoundException
     */
    public User loadUser() throws UsernameNotFoundException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsModelDTO userDetails = (UserDetailsModelDTO) authentication.getPrincipal();
            String username = ((UserDetailsModelDTO) authentication.getPrincipal()).getUsername();
            return userRepository.findByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }

    public User registerUser(User user) throws Exception {
        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new Exception("Error: Username is already taken!");
            }

            if (userRepository.existsByEmail(user.getEmail())) {
                throw new Exception("Error: Email is already in use!");
            }

            // Create new user's account
            user.setPassword(encoder.encode(user.getPassword()));

            List<Role> roles = new ArrayList<>();
            for (Role r : user.getRoles()) {
                //TODO CON QUALCOSA DI PIU DINAMICO
                if (r.getName() == ERole.USER) {
                    roles.add(roleRepository.findByName(ERole.USER));
                }
                if (r.getName() == ERole.ADMIN) {
                    roles.add(roleRepository.findByName(ERole.ADMIN));
                }
            }
            user.setRoles(roles);
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @param roles
     * @return
     */
    private Set<String> extractRoles(Set<Role> roles) {

        if (roles != null) {
            Set<String> rolesName = new HashSet<>();
            for (Role ruolo : roles) {
                rolesName.add(ruolo.getName().toString());
            }
            return rolesName;
        }
        return null;
    }
}



