package com.ex.oteluno.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebMessage {
    @JsonProperty("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}