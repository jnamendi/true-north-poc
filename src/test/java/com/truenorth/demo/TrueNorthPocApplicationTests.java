package com.truenorth.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.truenorth.demo.model.entity.Loan;
import com.truenorth.demo.service.ILoanMetricCalculator;
import com.truenorth.demo.service.LoanMetricFactory;
import com.truenorth.demo.service.impl.ConsumerLoanMetricCalculator;
import com.truenorth.demo.service.impl.StudentLoanMetricCalculator;

@SpringBootTest
class TrueNorthPocApplicationTests {
    
    @Autowired
    private LoanMetricFactory loanMetricFactory;
    
    @Test
    public void testFactoryGetLoanById() {
        Long id = 2l;
        Loan loan = loanMetricFactory.getLoan(id);
        assertEquals(id, loan.getLoanId());
    }
    
    @Test
    public void testFactoryGetAll() {
        List<Loan> loans = loanMetricFactory.getAll();
        assertEquals(4, loans.size());
    }
    
    @Test
    public void testFactoryGetInstanceStudentMetric() {
        Loan loan = loanMetricFactory.getLoan(2l);
        ILoanMetricCalculator metricInstance = loanMetricFactory.getInstance(loan);
        assertEquals(true, metricInstance instanceof StudentLoanMetricCalculator);
    }
    
    @Test
    public void testFactoryGetInstanceConsumerMetric() {
        Loan loan = loanMetricFactory.getLoan(1l);
        ILoanMetricCalculator metricInstance = loanMetricFactory.getInstance(loan);
        assertEquals(true, metricInstance instanceof ConsumerLoanMetricCalculator);
    }
    
    @Test
    public void testMetricCalculatorSupportStudent() {
        Loan studentLoan1 = loanMetricFactory.getLoan(2l);
        ILoanMetricCalculator metricInstance1 = loanMetricFactory.getInstance(studentLoan1);
        assertEquals(true, metricInstance1.isSupported(studentLoan1));
        
        Loan studentLoan2 = loanMetricFactory.getLoan(3l);
        ILoanMetricCalculator metricInstance2 = loanMetricFactory.getInstance(studentLoan2);
        assertEquals(false, metricInstance2.isSupported(studentLoan2));
        
        Loan studentLoan3 = loanMetricFactory.getLoan(4l);
        ILoanMetricCalculator metricInstance3 = loanMetricFactory.getInstance(studentLoan3);
        assertEquals(false, metricInstance3.isSupported(studentLoan3));
    }
    
    @Test
    public void testMetricCalculatorResult() {
        
    }
}
