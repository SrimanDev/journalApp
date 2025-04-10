package com.sriman.myJournalApp.repository;


import com.sriman.myJournalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);

    User deleteByUserName(String userName);
}
