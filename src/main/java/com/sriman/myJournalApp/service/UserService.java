package com.sriman.myJournalApp.service;

import com.sriman.myJournalApp.Entity.User;
import com.sriman.myJournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static final PasswordEncoder passwordEncoder= new  BCryptPasswordEncoder();

    

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));//Hashing users password to store in Db
            user.setRoles(Arrays.asList("USER"));

        }
        catch (Exception e)
        {
            log.info("Exception catch");
        }

    }

    public void saveNewAdminUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);

    }
    public User deleteByUserName(String userName){
        return userRepository.deleteByUserName(userName);
    }
}
