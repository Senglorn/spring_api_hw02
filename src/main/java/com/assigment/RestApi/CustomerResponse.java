package com.assigment.RestApi;

import java.time.LocalDateTime;

public class CustomerResponse<T> {
    private String message;
    private T customers;
    private LocalDateTime dateTime;
    private Integer status;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getCustomers() {
        return customers;
    }

    public void setCustomers(T customers) {
        this.customers = customers;
    }

    public CustomerResponse(LocalDateTime dateTime, Integer status, String message, T customers) {
        this.dateTime = dateTime;
        this.status = status;
        this.message = message;
        this.customers = customers;
    }
}
