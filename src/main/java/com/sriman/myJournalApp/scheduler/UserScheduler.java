package com.sriman.myJournalApp.scheduler;

import com.sriman.myJournalApp.cache.ApplicationCache;
import com.sriman.myJournalApp.service.EmailService;
import com.sriman.myJournalApp.service.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private ApplicationCache applicationCache;
}
