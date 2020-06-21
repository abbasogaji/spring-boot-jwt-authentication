package com.agrocheck.core.utils.api.dto;

public class SimpleResponseDto <T>{
    private boolean status;
    private T data;


    public SimpleResponseDto() {
    }

    public SimpleResponseDto(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
