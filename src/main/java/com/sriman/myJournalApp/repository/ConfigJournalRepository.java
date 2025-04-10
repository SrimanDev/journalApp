package com.sriman.myJournalApp.repository;

import com.sriman.myJournalApp.Entity.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RestController;


public interface ConfigJournalRepository extends MongoRepository<ConfigJournalApp, ObjectId> {
}
