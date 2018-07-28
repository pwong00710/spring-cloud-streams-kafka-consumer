package com.kaviddiss.streamkafka.model;

// lombok autogenerates getters, setters, toString() and a builder (see https://projectlombok.org/):
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaviddiss.streamkafka.config.AppSettings;
import lombok.*;

import java.util.concurrent.atomic.AtomicLong;

@Getter @Setter @ToString @Builder @AllArgsConstructor @NoArgsConstructor
public class Goodbyes {
    @Builder.Default private long id = Goodbyes.nextId();
    @Builder.Default private long timestamp = System.currentTimeMillis();
    private String name;
    private String message;
    private String refTxnId;
    @Builder.Default private int partitionId = AppSettings.partitionId;

    @JsonIgnore
    private static AtomicLong SEQ_ID = new AtomicLong();
    
    private static long nextId() {
        return SEQ_ID.incrementAndGet();
    }
}
