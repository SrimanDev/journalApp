package com.sriman.myJournalApp.cache;


import com.sriman.myJournalApp.Entity.ConfigJournalApp;
import com.sriman.myJournalApp.repository.ConfigJournalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApplicationCache {

    @Autowired
    private ConfigJournalRepository configJournalRepository;

    public Map<String,String> appCache ;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalApp> all = configJournalRepository.findAll();
        for (ConfigJournalApp configJournalApp:all){
            appCache.put(configJournalApp.getKey(),configJournalApp.getValue());
        }
    }
}
