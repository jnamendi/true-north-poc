package com.truenorth.demo.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truenorth.demo.enums.LoanType;
import com.truenorth.demo.model.entity.Loan;
import com.truenorth.demo.model.entity.LoanMetric;
import com.truenorth.demo.service.ILoanMetricCalculator;
import com.truenorth.demo.util.LoanMetricUtils;

@Service("consumerLoanMetricCalculator")
public class ConsumerLoanMetricCalculator implements ILoanMetricCalculator {
    
    @Autowired
    private LoanMetricUtils loanMetricUtils;
    
    @Override
    public boolean isSupported(Loan loan) {
        return false;
    }
    
    @Override
    public LoanMetric calculateLoanMetric(Loan loan) {
        LoanMetric result = new LoanMetric();
        
        if (loan.getAnnualInterest() == null) {
            result.setError(true);
            result.setErrorDescription("Loan don't have annual interest");
            return result;
        }
        
        if (loan.getRequestedAmount() == null) {
            result.setError(true);
            result.setErrorDescription("Loan don't have requested amount");
            return result;
        }
        
        Integer exponentPower = loanMetricUtils.getExponentPower(loan);
        
        if (exponentPower == 0) {
            result.setError(true);
            result.setErrorDescription("Loan don't have termYears or termMonths");
            return result;
        }
        
        BigDecimal monthlyInterestRate = loanMetricUtils.getMonthlyInterestRate(loan.getAnnualInterest());
        
        BigDecimal numerator = loanMetricUtils.getConsumerNumerator(loan.getRequestedAmount(), monthlyInterestRate);
        
        BigDecimal denominator = loanMetricUtils.getDenominator(monthlyInterestRate, exponentPower);
        
        result.setLoanId(loan.getLoanId());
        result.setLoanType(LoanType.CONSUMER.getType());
        result.setMonthlyRate(monthlyInterestRate);
        
        // divide and set precision to 4, round set scale with 2 precision and
        // round floor
        result.setMonthlyPayment(numerator.divide(denominator, new MathContext(4)).setScale(2, RoundingMode.FLOOR));
        
        return result;
    }
}
