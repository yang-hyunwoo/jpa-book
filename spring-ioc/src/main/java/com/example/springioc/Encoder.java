package com.example.springioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Encoder {

    private IEncoder iEncoder;

    public Encoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }
    public String encode(String message) {
        return iEncoder.encode(message);

    }

    public void setiEncoder(IEncoder iEncoder){
         this.iEncoder = iEncoder;

    }

}

