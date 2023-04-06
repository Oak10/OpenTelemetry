package com.ex.oteluno.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebMessageMainDouble {
    @JsonProperty("message")
    private String message="Main Message";
    @JsonProperty("web_message")
    private WebMessage webMessage;

    @JsonProperty("web_message_two")
    private WebMessage webMessageTwo;

    public WebMessage getWebMessageTwo() {
        return webMessageTwo;
    }

    public void setWebMessageTwo(WebMessage webMessageTwo) {
        this.webMessageTwo = webMessageTwo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WebMessage getWebMessage() {
        return webMessage;
    }

    public void setWebMessage(WebMessage webMessage) {
        this.webMessage = webMessage;
    }
}
