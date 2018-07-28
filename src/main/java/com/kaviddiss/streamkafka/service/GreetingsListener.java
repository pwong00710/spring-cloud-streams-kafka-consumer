package com.kaviddiss.streamkafka.service;

import com.kaviddiss.streamkafka.model.Goodbyes;
import com.kaviddiss.streamkafka.model.Greetings;
import com.kaviddiss.streamkafka.stream.GoodbyesStreams;
import com.kaviddiss.streamkafka.stream.GreetingsStreams;
import com.kaviddiss.streamkafka.stream.HelloStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component
@Slf4j
public class GreetingsListener {
    @Autowired
    private GreetingsStreams greetingsStreams;

    @Autowired
    private GoodbyesStreams goodbyesStreams;
    
    @StreamListener(HelloStreams.INPUT)
    @SendTo(HelloStreams.OUTPUT)
    public Goodbyes handleGreetings(@Payload Greetings greetings) {
        log.info("Received greetings: {}", greetings);
//        Goodbyes goodbyes = Goodbyes.builder()
//                .id(Goodbyes.nextId())
//                .name(greetings.getName())
//                .message(greetings.getMessage())
//                .timestamp(System.currentTimeMillis())
//                .refTxnId(greetings.getTxnId())
//                .build();
        Goodbyes goodbyes = new Goodbyes();
        goodbyes.setId(Goodbyes.nextId());
        goodbyes.setName(greetings.getName());
        goodbyes.setMessage(greetings.getMessage());
        goodbyes.setTimestamp(System.currentTimeMillis());
        goodbyes.setRefTxnId(greetings.getTxnId());
        
        log.info("Sending goodbyes {}", goodbyes);
        return goodbyes;
    }
}
