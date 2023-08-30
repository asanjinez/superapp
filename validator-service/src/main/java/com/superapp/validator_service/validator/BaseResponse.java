package com.superapp.validator_service.validator;

public record BaseResponse(String[] errors) {
    public boolean hasErrors() {
        return this.errors != null && errors.length > 0;
    }
}
