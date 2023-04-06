package com.ex.oteluno.controllers;

import com.ex.oteluno.api.client.WebMessageClient;
import com.ex.oteluno.models.WebMessage;
import com.ex.oteluno.models.WebMessageMain;
import com.ex.oteluno.models.WebMessageMainDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@RestController
@RequestMapping(value = "/webmain")
public class WebMessageMainController {
    WebMessageClient webMessageClient;

    @Autowired
    public WebMessageMainController(WebMessageClient webMessageClient) {
        this.webMessageClient = webMessageClient;
    }

    @GetMapping(path = "/message/{id}")
    public WebMessageMain getMessageOnlyOtelDos(@PathVariable("id") long someId){
        WebMessageMain webMessageMain = new WebMessageMain();
        webMessageMain.setWebMessage(webMessageClient.getMessageNoError(someId));
        return webMessageMain;
    }

    @GetMapping(path = "/message/full-path")
    public WebMessageMain getMessageFullPath(){
        WebMessageMain webMessageMain = new WebMessageMain();
        webMessageMain.setWebMessage(webMessageClient.getKafkaSendMessage());
        return webMessageMain;
    }

    @GetMapping(path = "/message/otel-dos/not-found")
    public WebMessageMain getMessageOtelDosNotFound(){
        WebMessageMain webMessageMain = new WebMessageMain();
        webMessageMain.setWebMessage(webMessageClient.getMessageNotFound());
        return webMessageMain;
    }

    @GetMapping(path = "/message/otel-dos/internal-error")
    public WebMessageMain getMessageOtelDosInternalError(){
        WebMessageMain webMessageMain = new WebMessageMain();
        webMessageMain.setWebMessage(webMessageClient.getMessageInternalError());
        return webMessageMain;
    }

    @GetMapping(path = "/message/internal-throw")
    public WebMessageMain getMessageInternalThrow(){
        throw new RuntimeException();
    }

    @GetMapping(path = "/message/two-requests-with-delay")
    public WebMessageMainDouble getMessageWithDelay(){
        WebMessageMainDouble webMessageMainDouble = new WebMessageMainDouble();
        //otel-dos
        webMessageMainDouble.setWebMessage(webMessageClient.getMessageWithDelay(2));
        // Otel-cuatro
        webMessageMainDouble.setWebMessageTwo(webMessageClient.getMessageWithDelayOtelCuatro(2));
        return webMessageMainDouble;
    }

    @GetMapping(path = "/message/two-requests-with-delay-async")
    public WebMessageMainDouble getMessageWithDelayAsync() throws ExecutionException, InterruptedException {
        WebMessageMainDouble webMessageMainDouble = new WebMessageMainDouble();
        Future<WebMessage> future = webMessageClient.getMessageWithDelayAsync(2);
        // Otel cuatro
        webMessageMainDouble.setWebMessage(webMessageClient.getMessageWithDelayOtelCuatro(2));
        // Otel dos
        webMessageMainDouble.setWebMessageTwo(future.get());
        return webMessageMainDouble;
    }

}
