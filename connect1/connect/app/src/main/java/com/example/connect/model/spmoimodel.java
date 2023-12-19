package com.example.connect.model;

import java.util.List;

public class spmoimodel {
    boolean success;
    String message;
    List<spmoi> result;

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

    public List<spmoi> getResult() {
        return result;
    }

    public void setResult(List<spmoi> result) {
        this.result = result;
    }
}
