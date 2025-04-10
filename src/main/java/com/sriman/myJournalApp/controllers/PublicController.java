package com.sriman.myJournalApp.controllers;

import com.sriman.myJournalApp.Entity.User;
import com.sriman.myJournalApp.service.UserDetailsServiceImpl;
import com.sriman.myJournalApp.service.UserService;
import com.sriman.myJournalApp.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private  UserService userService;

   @Autowired
   private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public User createUser(@RequestBody User user){
        userService.saveNewUser(user);
        return user;
    }

//    @PostMapping("/login")
//    public void login(@RequestBody User user){
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
//        }
//        catch (Exception e){
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
