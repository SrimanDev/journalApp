package com.sriman.myJournalApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigJournalApp {

    @Id
    private ObjectId id;
    private String key;
    private String value;

}
