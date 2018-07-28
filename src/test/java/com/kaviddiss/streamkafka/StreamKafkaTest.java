package com.kaviddiss.streamkafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaviddiss.streamkafka.model.Goodbyes;
import com.kaviddiss.streamkafka.model.Greetings;
import com.kaviddiss.streamkafka.stream.HelloStreams;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StreamKafkaTest {

    @Autowired
    private HelloStreams helloStreams;

    @Autowired
    private MessageCollector messageCollector;

    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    public void testGreeting() throws Exception {
        String name = "Peter";
        String message = "hello";
        
        Greetings greetings = new Greetings();
        greetings.setId(Greetings.nextId());
        greetings.setTxnId(Greetings.nextTxnId());
        greetings.setName(name);
        greetings.setMessage(message);
        greetings.setTimestamp(System.currentTimeMillis());

        log.info("Sending greetings {}", greetings);

        helloStreams.inboundGreetings().send(MessageBuilder
                .withPayload(greetings)
                .build());

        Message<String> received = (Message<String>) messageCollector.forChannel(helloStreams.outboundGoodbyes()).poll();

        Goodbyes goodbyes = objectMapper.readValue(received.getPayload(), Goodbyes.class);
        
        Assert.assertNotNull(goodbyes);
        log.info("Received goodbyes: {}", goodbyes);
        
        Assert.assertEquals(goodbyes.getRefTxnId(), greetings.getTxnId());
    }
}
