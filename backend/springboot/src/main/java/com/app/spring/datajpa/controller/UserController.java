package com.app.spring.datajpa.controller;

import jakarta.servlet.http.HttpServletRequest;
import com.app.spring.datajpa.repository.BlacklistTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.spring.datajpa.model.User;
import com.app.spring.datajpa.model.UserAndToken;
import com.app.spring.datajpa.model.BlacklistToken;
import com.app.spring.datajpa.repository.UserRepository;
import com.app.spring.datajpa.security.jwt.AuthTokenFilter;
import com.app.spring.datajpa.security.jwt.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private BlacklistTokenRepository BlacklistTokenRepository;

    @GetMapping("/profile")
    public ResponseEntity<User> profile() {

        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            User user = UserRepository.findByUsername(userDetails.getUsername()).get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserAndToken> loginUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            User user_ = UserRepository.findByUsername(user.getUsername()).get();
            UserAndToken userToken = new UserAndToken(jwt, user_);

            return new ResponseEntity<>(userToken, HttpStatus.OK);

        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {

            if (UserRepository.existsByUsername(user.getUsername()) > 0) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else if (UserRepository.existsByUsername(user.getEmail()) > 0) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                user.setPassword(encoder.encode(user.getPassword()));
                UserRepository.save(user);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        try {
            String token = authTokenFilter.parseJwt(request);
            if (BlacklistTokenRepository.TokenExist(token) == 0) {
                BlacklistToken blacklistToken = new BlacklistToken();
                blacklistToken.setToken(token);
                BlacklistTokenRepository.save(blacklistToken);
            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
