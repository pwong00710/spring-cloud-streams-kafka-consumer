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
    private HelloStreams helloStreams;
    
    @StreamListener(target = HelloStreams.INPUT, condition = "headers['type']=='greetings'")
//    @SendTo(HelloStreams.OUTPUT)
    public void handleGreetings(@Payload Greetings greetings) {
        log.info("Received greetings: {}", greetings);
        Goodbyes goodbyes = Goodbyes.builder()
                .name(greetings.getName())
                .message(greetings.getMessage())
                .refTxnId(greetings.getTxnId())
                .build();
 
        log.info("Sending goodbyes {}", goodbyes);
        helloStreams.outboundGoodbyes().send(MessageBuilder
                .withPayload(goodbyes)
                .setHeader("type", "goodbyes")
                .build());
//        return goodbyes;
    }
}
