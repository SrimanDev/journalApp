package com.sriman.myJournalApp.controllers;

import com.sriman.myJournalApp.Entity.User;
import com.sriman.myJournalApp.api.response.WeatherResponse;
import com.sriman.myJournalApp.service.UserService;
import com.sriman.myJournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    //All users should be visible to admin only
//    @GetMapping
//    public List<User> getAll(){
//        return userService.getAllUsers();
//    }


    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userNameInDb = userService.findByUserName(userName);
        //if(userNameInDb!=null){
        // Now user is authenticated no need of above step
            userNameInDb.setUserName(user.getUserName());
            userNameInDb.setPassword(user.getPassword());
            //userInDb has Id , by saving user we are just updating in the same User
            userService.saveNewUser(userNameInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping()
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        //User userInDb = userService.findByUserName(userName);
        userService.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("weather")
//    public ResponseEntity<?> weatherStack(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//
//        WeatherResponse weatherResponse = weatherService.getWeather("Hyderabad");
//        String weatherFellsLike="";
//        if(weatherResponse!=null){
//            weatherFellsLike=weatherResponse.getCurrent().getFeelsLike();
//        }
//        return new ResponseEntity<>("Hi "+ userName + " Weather feels like "+ weatherFellsLike, HttpStatus.OK);
   // }
}
