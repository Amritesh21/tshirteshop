package com.tshirtShop.serverSide.publicAPIs.DTO;

public class UploadResponse {

    private String message;
    private String status;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
