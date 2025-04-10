//package com.sriman.myJournalApp.controllers;
//
//import com.sriman.myJournalApp.Entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//
//   private Map<Long, JournalEntry> journalEntries= new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>( journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean postEntry(@RequestBody JournalEntry journalEntry){
//        journalEntries.put(journalEntry.getId(),journalEntry);
//        return true;
//    }
//
//    @GetMapping("/id{myId}")
//    public JournalEntry getById(@PathVariable Long myId){
//        return journalEntries.get(myId);
//    }
//
//    @PutMapping
//    public JournalEntry putById(@PathVariable Long myId, @RequestBody JournalEntry journalEntry){
//        return journalEntries.put(myId,journalEntry);
//    }
//
//    @DeleteMapping("id/{myId}")
//    public JournalEntry deleteById(@PathVariable Long myId){
//        return journalEntries.remove(myId);
//    }
//}
