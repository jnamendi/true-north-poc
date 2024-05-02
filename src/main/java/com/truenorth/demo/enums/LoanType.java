package com.truenorth.demo.enums;

public enum LoanType {
    CONSUMER("consumer"), STUDENT("student");
    
    private String type;
    
    LoanType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
}
