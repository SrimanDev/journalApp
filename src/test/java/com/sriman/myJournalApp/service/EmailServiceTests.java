package com.sriman.myJournalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    EmailService emailService;

    @Test
    void testSendEmail(){
        emailService.sendEmail("venkatasriman17@gmail.com",
                "Journal App",
                "Message coming from spring boot journalApp");
    }
}
