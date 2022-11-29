package com.app.forumchat.controllers.auth;

import com.app.forumchat.models.auth.JwtResponseDTO;
import com.app.forumchat.models.auth.User;
import com.app.forumchat.models.auth.UserDetailsModelDTO;
import com.app.forumchat.repos.auth.RoleRepository;
import com.app.forumchat.repos.auth.UserRepository;
import com.app.forumchat.services.UserService;
import com.app.forumchat.services.auth.AuthUserService;
import com.app.forumchat.services.auth.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/it/elisa/")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthUserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthController() {
    }

    /**
     * Controller method for login
     *
     * @param loginRequest
     * @return
     */
    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {
        try {
            System.out.println(loginRequest.toString());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsModelDTO userDetails = (UserDetailsModelDTO) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponseDTO(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Controller method for registration
     *
     * @param appUser
     * @return
     */
    @PostMapping("signup")
    public ResponseEntity<?> RegisterUser(@RequestBody User appUser) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.registerUser(appUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("info")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(new User());
    }
}

