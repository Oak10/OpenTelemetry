package com.ex.oteldos.controllers;

import com.ex.oteldos.exceptions.WebMessageNotFoundException;
import com.ex.oteldos.exceptions.WebMessageInternalError;
import com.ex.oteldos.models.WebMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/web")
public class WebController {
    private static final String NOT_FOUND_MSG_STRING="WebMessage <__ Not Found __>";
    private static final String INTERNAL_ERROR_MSG_STRING="WebMessage <__Internal Error__>";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
    @GetMapping(path = "/message/{id}")
    public WebMessage getWebMessage(@PathVariable("id") long messageId){
        WebMessage webMessage = new WebMessage();
        webMessage.setMessage("message from otel-dos id <" + messageId + ">");
        LOGGER.info(webMessage.getMessage());
        return webMessage;
    }

    @GetMapping(path = "/message/not-found")
    public WebMessage getWebMessageNotFound(){
        LOGGER.info(NOT_FOUND_MSG_STRING);
        throw new WebMessageNotFoundException(NOT_FOUND_MSG_STRING);
    }

    @GetMapping(path = "/message/internal-error")
    public WebMessage getWebMessageInternalError(){
        LOGGER.info(INTERNAL_ERROR_MSG_STRING);
        throw new WebMessageInternalError(INTERNAL_ERROR_MSG_STRING);
    }


    @GetMapping(path = "/message/delay/{seconds}")
    public WebMessage getWebMessageDelay(@PathVariable("seconds") int delay) throws InterruptedException {
        WebMessage webMessage = new WebMessage();
        webMessage.setMessage("message from otel-dos - delay = <" + delay + ">");
        LOGGER.info(webMessage.getMessage());
        Thread.sleep(delay * 1000L);
        return webMessage;
    }

}
