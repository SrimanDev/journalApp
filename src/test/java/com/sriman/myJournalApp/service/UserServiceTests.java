package com.sriman.myJournalApp.service;

import com.sriman.myJournalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd(){
        assertEquals(8, 5+3);
    }


    @ParameterizedTest
    @CsvSource(
            {
                    "Ram",
                    "Krishna",
                    "Ravan"
            }
    )
    public void testUserName(String name){
        assertNotNull(userRepository.findByUserName(name));
     }

    @ParameterizedTest
    @ValueSource(strings =
            {
                    "Ram",
                    "krishna",
                    "Ravan"
            }
    )
    public void testUserName2(String name){
        assertNotNull(userRepository.findByUserName(name));
    }

}
