package com.example.connect.model;

import java.util.List;

public class Loaispmodel {
    boolean success;
    String message;
    List<loaisp> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<loaisp> getResult() {
        return result;
    }

    public void setResult(List<loaisp> result) {
        this.result = result;
    }
}
