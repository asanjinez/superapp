package com.superapp.bill_service.bill;

public record BaseResponse(String[] errors) {
    public boolean hasErrors() {
        return this.errors != null && errors.length > 0;
    }

    public String[] getErrors(){
        return this.errors;
    }
}
