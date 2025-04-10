package com.sriman.myJournalApp.service;

import com.sriman.myJournalApp.Entity.JournalEntry;
import com.sriman.myJournalApp.Entity.User;
import com.sriman.myJournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);

        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);//adding above save journal entry into user
        userService.saveUser(user);//saving user after adding journal entries to user

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getEntries(){
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id,String userName){
        boolean removed=false;
        try {
            User user = userService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {//Here , removed is true, otherwise journal entry list cant delete the specified journal entry

                userService.saveUser(user);//updating the user after removing the deleted Journal reference
                journalEntryRepository.deleteById(id); //Journal entry reference in user does not get automatically removed
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw  new RuntimeException("error occured while deleteing entry", e);
        }
        return removed;
    }
}
