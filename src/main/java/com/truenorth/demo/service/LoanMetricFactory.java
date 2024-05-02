package com.truenorth.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.truenorth.demo.enums.LoanType;
import com.truenorth.demo.model.entity.Loan;
import com.truenorth.demo.util.LoanGeneratonUtil;

@Service
public class LoanMetricFactory {
    
    @Autowired
    @Qualifier("studentLoanMetricCalculator")
    private ILoanMetricCalculator studentLoanMetricCalculator;
    
    @Autowired
    @Qualifier("consumerLoanMetricCalculator")
    private ILoanMetricCalculator consumerLoanMetricCalculator;
    
    public ILoanMetricCalculator getInstance(Loan loan) {
        if (LoanType.CONSUMER.getType().equals(loan.getType())) {
            return consumerLoanMetricCalculator;
        }
        
        if (LoanType.STUDENT.getType().equals(loan.getType())) {
            return studentLoanMetricCalculator;
        }
        
        // If non type match with LoanType return null.
        return null;
    }
    
    public Loan getLoan(Long loanId) {
        return LoanGeneratonUtil.loanDatabase.stream().filter(l -> l.getLoanId().equals(loanId)).findFirst()
                .orElse(null);
    }
    
    public List<Loan> getAll() {
        return LoanGeneratonUtil.loanDatabase;
    }
    
}
