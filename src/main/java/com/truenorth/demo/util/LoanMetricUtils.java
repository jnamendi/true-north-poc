package com.truenorth.demo.util;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.stereotype.Component;

import com.truenorth.demo.model.entity.Loan;

@Component
public class LoanMetricUtils {
    
    public Integer getExponentPower(Loan loan) {
        Integer powerNumber = 0;
        if (loan.getTermMonths() == 0) {
            powerNumber = powerYear(loan.getTermYears());
        } else {
            powerNumber = powerMonth(loan.getTermMonths());
        }
        return powerNumber;
    }
    
    private Integer powerYear(Integer years) {
        if (years == null) {
            return 0;
        }
        return -1 * years * 12;
    }
    
    private Integer powerMonth(Integer months) {
        if (months == null) {
            return 0;
        }
        return -1 * months;
    }
    
    public BigDecimal getMonthlyInterestRate(BigDecimal annualInterest) {
        return annualInterest.divide(BigDecimal.valueOf(12)).divide(BigDecimal.valueOf(100));
    }
    
    public BigDecimal getDenominator(BigDecimal monthlyInterestRate, Integer exponentPower) {
        return BigDecimal.valueOf(1).add(
                monthlyInterestRate.add(BigDecimal.valueOf(1)).pow(exponentPower, MathContext.DECIMAL128).negate());
    }
    
    public BigDecimal getConsumerNumerator(BigDecimal requestedAmount, BigDecimal monthlyInterestRate) {
        return requestedAmount.multiply(monthlyInterestRate);
    }
    
    public BigDecimal getStudentNumerator(BigDecimal requestedAmount, BigDecimal monthlyInterestRate) {
        return BigDecimal.valueOf(0.8).multiply(requestedAmount).multiply(monthlyInterestRate);
    }

    
}
