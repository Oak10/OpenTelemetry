package com.ex.otelcuatro.controllers;

import com.ex.otelcuatro.models.WebMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/web/otel-cuatro")
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);


    @GetMapping(path = "/message/delay/{seconds}")
    public WebMessage getWebMessageDelay(@PathVariable("seconds") int delay) throws InterruptedException {
        WebMessage webMessage = new WebMessage();
        webMessage.setMessage("message from otel-cuatro - delay = <" + delay + ">");
        LOGGER.info(webMessage.getMessage());
        Thread.sleep(delay * 1000L);
        return webMessage;
    }

}
