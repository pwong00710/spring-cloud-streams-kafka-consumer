package com.kaviddiss.streamkafka.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface GreetingsStreams {
    String INPUT = "greetings-in";

    @Input(INPUT)
    SubscribableChannel inboundGreetings();
}
