package com.example;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(Sink.class)
public class SimpleSinkBean {

    
    @StreamListener(value=Sink.INPUT)
    public void receive(GenericMessage<String> pMessage){
        System.out.println(pMessage);
    }
}
