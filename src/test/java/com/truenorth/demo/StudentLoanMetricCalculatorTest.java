package com.truenorth.demo;

import com.truenorth.demo.model.entity.Borrower;
import com.truenorth.demo.model.entity.Loan;
import com.truenorth.demo.model.entity.LoanMetric;
import com.truenorth.demo.service.impl.StudentLoanMetricCalculator;
import com.truenorth.demo.util.LoanMetricUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class StudentLoanMetricCalculatorTest {
    @InjectMocks
    private StudentLoanMetricCalculator studentLoanMetricCalculator;
    
    @Mock
    LoanMetricUtils loanMetricUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void studentLoanMetricCalculatorSupportsValidLoan() {
        Loan loan = new Loan();
        loan.setBorrower(new Borrower());
        loan.getBorrower().setAge(20);
        assertTrue(studentLoanMetricCalculator.isSupported(loan));
    }

    @Test
    public void studentLoanMetricCalculatorDoesNotSupportLoanWithNullBorrower() {
        Loan loan = new Loan();
        assertFalse(studentLoanMetricCalculator.isSupported(loan));
    }

    @Test
    public void studentLoanMetricCalculatorDoesNotSupportLoanWithBorrowerAgeOutOfRange() {
        Loan loan = new Loan();
        loan.setBorrower(new Borrower());
        loan.getBorrower().setAge(31);
        assertFalse(studentLoanMetricCalculator.isSupported(loan));
    }

    @Test
    public void calculateLoanMetricReturnsErrorForUnsupportedLoan() {
        Loan loan = new Loan();
        loan.setBorrower(new Borrower());
        loan.getBorrower().setAge(31);
        LoanMetric result = studentLoanMetricCalculator.calculateLoanMetric(loan);
        assertTrue(result.isError());
        assertEquals("Metric not support loan with id = null", result.getErrorDescription());
    }

    @Test
    public void calculateLoanMetricReturnsErrorForLoanWithoutAnnualInterest() {
        Loan loan = new Loan();
        loan.setBorrower(new Borrower());
        loan.getBorrower().setAge(20);
        LoanMetric result = studentLoanMetricCalculator.calculateLoanMetric(loan);
        assertTrue(result.isError());
        assertEquals("Loan don't have annual interest", result.getErrorDescription());
    }

    @Test
    public void calculateLoanMetricReturnsErrorForLoanWithoutRequestedAmount() {
        Loan loan = new Loan();
        loan.setBorrower(new Borrower());
        loan.getBorrower().setAge(20);
        loan.setAnnualInterest(BigDecimal.ONE);
        LoanMetric result = studentLoanMetricCalculator.calculateLoanMetric(loan);
        assertTrue(result.isError());
        assertEquals("Loan don't have requested amount", result.getErrorDescription());
    }

    @Test
    public void calculateLoanMetricReturnsErrorForLoanWithoutTermYearsOrTermMonths() {
        Loan loan = new Loan();
        loan.setBorrower(new Borrower());
        loan.getBorrower().setAge(20);
        loan.setAnnualInterest(BigDecimal.ONE);
        loan.setRequestedAmount(BigDecimal.TEN);
        LoanMetric result = studentLoanMetricCalculator.calculateLoanMetric(loan);
        assertTrue(result.isError());
        assertEquals("Loan don't have termYears or termMonths", result.getErrorDescription());
    }
}
