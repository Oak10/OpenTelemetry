package com.ex.oteldos.controllers;

import com.ex.oteldos.models.WebMessage;
import com.ex.oteldos.services.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaWebController {
    Random r = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaWebController.class);
    @Autowired
    KafkaSender kafkaSender;

    @GetMapping(value = "/produce")
    public WebMessage produce() {
        WebMessage webMessage = new WebMessage();
        int rand = r.nextInt(1000);
        String loggerMsg = "Message sent <kafka random int - " + rand +">";
        webMessage.setMessage("Message from otel-dos <" + loggerMsg + ">");
        LOGGER.info(loggerMsg);
        kafkaSender.send("kafka random int - " + rand);
        return webMessage;
    }

}