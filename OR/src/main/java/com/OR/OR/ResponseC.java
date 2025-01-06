package com.OR.OR;

public class ResponseC<T> {
    private String status;
    private String message;
    private T response;

    public ResponseC(String status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}