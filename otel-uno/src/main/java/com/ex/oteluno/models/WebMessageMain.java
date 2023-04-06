package com.ex.oteluno.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebMessageMain {
    @JsonProperty("message")
    private String message="Main Message";
    @JsonProperty("web_message")
    private WebMessage webMessage;

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
