package com.kaviddiss.streamkafka.model;

// lombok autogenerates getters, setters, toString() and a builder (see https://projectlombok.org/):
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaviddiss.streamkafka.config.AppSettings;
import lombok.*;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Getter @Setter @ToString @Builder @AllArgsConstructor @NoArgsConstructor
public class Greetings {
    @Builder.Default private long id = Greetings.nextId();
    @Builder.Default private long timestamp = System.currentTimeMillis();
    private String name;    
    private String message;
    @Builder.Default private String txnId = Greetings.nextTxnId();
    @Builder.Default private int partitionId = AppSettings.partitionId;

    @JsonIgnore
    private static AtomicLong SEQ_ID = new AtomicLong();
    
    private static long nextId() {
        return SEQ_ID.incrementAndGet();
    }
    
    private static String nextTxnId() {
        return UUID.randomUUID().toString();
    }
}
