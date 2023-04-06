package com.ex.oteluno.api.client;

import com.ex.oteluno.models.WebMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class WebMessageClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebMessageClient.class);
    private final RestTemplate restTemplate;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Value("${serviceDos.baseUrl}")
    private String baseUrlOtelDos;

    @Value("${serviceCuatro.baseUrl}")
    private String baseUrlOtelCuatro;

    @Autowired
    public WebMessageClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WebMessage getMessageNoError(long messageId){
        LOGGER.info("Fetching Message from otel-dos {}", messageId);
        String url = String.format("%s/web/message/%d", baseUrlOtelDos, messageId);
        ResponseEntity<WebMessage> webMessage = restTemplate.getForEntity(url, WebMessage.class);
        return webMessage.getBody();
    }

    public WebMessage getMessageInternalError(){
        LOGGER.info("Getting internal error from otel-dos");
        String url = String.format("%s/web/message/internal-error", baseUrlOtelDos);
        ResponseEntity<WebMessage> webMessage = restTemplate.getForEntity(url, WebMessage.class);
        return webMessage.getBody();
    }

    public WebMessage getMessageNotFound(){
        LOGGER.info("Getting not Found from otel-dos");
        String url = String.format("%s/web/message/not-found", baseUrlOtelDos);
        ResponseEntity<WebMessage> webMessage = restTemplate.getForEntity(url, WebMessage.class);
        return webMessage.getBody();
    }

    public WebMessage getKafkaSendMessage(){
        LOGGER.info("Asking otel-dos to publish to kafka");
        String url = String.format("%s/kafka/produce", baseUrlOtelDos);
        ResponseEntity<WebMessage> webMessage = restTemplate.getForEntity(url, WebMessage.class);
        return webMessage.getBody();
    }

    public WebMessage getMessageWithDelay(long seconds){
        LOGGER.info("Getting message from otel-dos with delay {}", seconds);
        String url = String.format("%s/web/message/delay/%d", baseUrlOtelDos, seconds);
        ResponseEntity<WebMessage> webMessage = restTemplate.getForEntity(url, WebMessage.class);
        return webMessage.getBody();
    }

    /*public Future<WebMessage> getMessageWithDelayAsync(long seconds){
        LOGGER.info("Getting message Async from otel-dos with delay {}", seconds);
        String url = String.format("%s/web/message/delay/%d", baseUrlOtelDos, seconds);
        return executor.submit( () -> {
            return restTemplate.getForEntity(url, WebMessage.class).getBody();
        });
    }*/

    @Async
    public Future<WebMessage> getMessageWithDelayAsync(long seconds){
        LOGGER.info("Getting message Async from otel-dos with delay {}", seconds);
        String url = String.format("%s/web/message/delay/%d", baseUrlOtelDos, seconds);
        return new AsyncResult<WebMessage>(restTemplate.getForEntity(url, WebMessage.class).getBody());
        /*return executor.submit( () -> {
            return restTemplate.getForEntity(url, WebMessage.class).getBody();
        });*/
    }


    public WebMessage getMessageWithDelayOtelCuatro(long seconds){
        LOGGER.info("Getting message from otel-dos with delay {}", seconds);
        String url = String.format("%s/web/otel-cuatro/message/delay/%d", baseUrlOtelCuatro, seconds);
        ResponseEntity<WebMessage> webMessage = restTemplate.getForEntity(url, WebMessage.class);
        return webMessage.getBody();
    }


}
