package com.sriman.myJournalApp.controllers;

import com.sriman.myJournalApp.Entity.JournalEntry;
import com.sriman.myJournalApp.Entity.User;
import com.sriman.myJournalApp.service.JournalEntryService;
import com.sriman.myJournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalEntryService journalEntryService;
    
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=  userService.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        //Above line, fetching journal entries associated with a user
        //List<JournalEntry> entries = journalEntryService.getEntries();
        if(journalEntries!=null && !journalEntries.isEmpty()){
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            journalEntryService.saveEntry(journalEntry, userName);
            return new ResponseEntity<>( journalEntry,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).
                                                                                        collect((Collectors.toList()));
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
//
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean removed = journalEntryService.deleteById(myId, userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId,
                                                        @RequestBody JournalEntry newJournalEntry) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if (journalEntry.isPresent()) {
                JournalEntry journalEntryInDb = journalEntry.get();

                journalEntryInDb.setTitle(newJournalEntry.getTitle() != null && !newJournalEntry.getTitle().equals("") ? newJournalEntry.getTitle() : journalEntryInDb.getTitle());
                journalEntryInDb.setContent(newJournalEntry.getContent() != null && !newJournalEntry.getContent().equals("") ? newJournalEntry.getContent() : journalEntryInDb.getContent());
                journalEntryService.saveEntry(journalEntryInDb);

                return new ResponseEntity<>(journalEntryInDb, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    }


