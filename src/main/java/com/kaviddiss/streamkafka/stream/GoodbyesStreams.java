package com.kaviddiss.streamkafka.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface GoodbyesStreams {
    String OUTPUT = "goodbyes-out";

    @Output(OUTPUT)
    MessageChannel outboundGoodbyes();
}
