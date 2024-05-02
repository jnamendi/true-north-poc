package com.truenorth.demo.service;


import com.truenorth.demo.model.entity.Loan;
import com.truenorth.demo.model.entity.LoanMetric;

public interface ILoanMetricCalculator {
    public LoanMetric calculateLoanMetric(Loan loan);
    
    public boolean isSupported(Loan loan);
}
