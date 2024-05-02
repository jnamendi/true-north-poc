package com.truenorth.demo.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.truenorth.demo.model.entity.Loan;
import com.truenorth.demo.model.entity.LoanMetric;
import com.truenorth.demo.service.ILoanMetricCalculator;
import com.truenorth.demo.service.LoanMetricFactory;

@RestController()
@RequestMapping("/loan")
public class LoanController {
    
    @Autowired
    private LoanMetricFactory loanMetricFactory;
    
    @GetMapping("/{id}")
    public Loan getLoan(@PathVariable Long id) {
        Loan loan = loanMetricFactory.getLoan(id);
        if (loan == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return loan;
    }
    
    @GetMapping("/{id}/calculate")
    public LoanMetric calculateLoanMetric(@PathVariable Long id) {
        LoanMetric result = new LoanMetric();
        
        Loan loan = loanMetricFactory.getLoan(id);
        if (loan == null) {
            result.setError(true);
            result.setErrorDescription("Not found loan with id = " + id);
            return result;
        }
        
        ILoanMetricCalculator loanMetricCalculator = loanMetricFactory.getInstance(loan);
        if (loanMetricCalculator == null) {
            result.setError(true);
            result.setErrorDescription("Not found LoanMetricCalculator with type = " + loan.getType());
            return result;
        }
        
        return loanMetricCalculator.calculateLoanMetric(loan);
    }
    
    @GetMapping("/max-monthly-payment")
    public Loan getMaxMonthlyPaymentLoan() {
        List<Loan> loans = loanMetricFactory.getAll();
        
        // Get all loan metric
        List<LoanMetric> allLoanMetric = loans.stream()
                .map(l -> loanMetricFactory.getInstance(l).calculateLoanMetric(l)).collect(Collectors.toList());
        
        // Get the max monthly payment metric
        LoanMetric maxLoan = allLoanMetric.stream().filter(m -> !m.isError())
                .max(Comparator.comparing(LoanMetric::getMonthlyPayment)).get();
        
        if (maxLoan == null) {
            return null;
        }
        
        return loanMetricFactory.getLoan(maxLoan.getLoanId());
    }
    
}
