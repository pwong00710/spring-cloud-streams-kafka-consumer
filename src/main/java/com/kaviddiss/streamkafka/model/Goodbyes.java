package com.kaviddiss.streamkafka.model;

// lombok autogenerates getters, setters, toString() and a builder (see https://projectlombok.org/):

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaviddiss.streamkafka.config.AppSettings;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.atomic.AtomicLong;

@Getter @Setter @ToString @Builder @AllArgsConstructor @NoArgsConstructor
public class Goodbyes {
    private long id;
    private long timestamp;
    private String name;
    private String message;
    private String refTxnId;
    private int partitionId = AppSettings.partitionId;

    @JsonIgnore
    private static AtomicLong SEQ_ID = new AtomicLong();
    
    public static long nextId() {
        return SEQ_ID.incrementAndGet();
    }
}
