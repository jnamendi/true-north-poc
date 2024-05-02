package com.truenorth.demo.model.entity;

import java.math.BigDecimal;

public class Loan {
    private Long loanId;
    
    private BigDecimal requestedAmount;
    
    private Integer termYears;
    
    private Integer termMonths;
    
    private BigDecimal annualInterest;
    
    private String type;
    
    private Long loanOfficerId;
    
    private Borrower borrower;
    
    public Long getLoanId() {
        return loanId;
    }
    
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }
    
    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }
    
    public Integer getTermYears() {
        return termYears;
    }
    
    public void setTermYears(Integer termYears) {
        this.termYears = termYears;
    }
    
    public Integer getTermMonths() {
        return termMonths;
    }
    
    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }
    
    public BigDecimal getAnnualInterest() {
        return annualInterest;
    }
    
    public void setAnnualInterest(BigDecimal annualInterest) {
        this.annualInterest = annualInterest;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Long getLoanOfficerId() {
        return loanOfficerId;
    }
    
    public void setLoanOfficerId(Long loanOfficerId) {
        this.loanOfficerId = loanOfficerId;
    }
    
    public Borrower getBorrower() {
        return borrower;
    }
    
    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
    
}
