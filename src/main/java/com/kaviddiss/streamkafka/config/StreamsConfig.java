package com.kaviddiss.streamkafka.config;

import com.kaviddiss.streamkafka.stream.GoodbyesStreams;
import com.kaviddiss.streamkafka.stream.GreetingsStreams;
import com.kaviddiss.streamkafka.stream.HelloStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({HelloStreams.class})
public class StreamsConfig {
}
