package com.truenorth.demo.model.entity;

import java.math.BigDecimal;

public class Borrower {
    private String name;
    
    private BigDecimal annualIncome;
    
    private boolean delinquentDebt;
    
    private BigDecimal annualDebt;
    
    private Integer creditHistory;
    
    private Integer age;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }
    
    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }
    
    public boolean isDelinquentDebt() {
        return delinquentDebt;
    }
    
    public void setDelinquentDebt(boolean delinquentDebt) {
        this.delinquentDebt = delinquentDebt;
    }
    
    public BigDecimal getAnnualDebt() {
        return annualDebt;
    }
    
    public void setAnnualDebt(BigDecimal annualDebt) {
        this.annualDebt = annualDebt;
    }
    
    public Integer getCreditHistory() {
        return creditHistory;
    }
    
    public void setCreditHistory(Integer creditHistory) {
        this.creditHistory = creditHistory;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
}
