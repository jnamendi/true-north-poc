package com.truenorth.demo;

import com.truenorth.demo.model.entity.Loan;
import com.truenorth.demo.model.entity.LoanMetric;
import com.truenorth.demo.service.impl.ConsumerLoanMetricCalculator;
import com.truenorth.demo.util.LoanMetricUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConsumerLoanMetricCalculatorTest {

    @Mock
    LoanMetricUtils loanMetricUtils;

    @InjectMocks
    ConsumerLoanMetricCalculator consumerLoanMetricCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //
    @Test
    void calculateLoanMetricSetsError() {
        Loan loan = new Loan();
        loan.setAnnualInterest(null);
        assertLoanMetricError(loan, "Loan don't have annual interest");

        loan.setAnnualInterest(BigDecimal.ONE);
        loan.setRequestedAmount(null);
        assertLoanMetricError(loan, "Loan don't have requested amount");

        loan.setAnnualInterest(BigDecimal.ONE);
        loan.setRequestedAmount(BigDecimal.TEN);
        when(loanMetricUtils.getExponentPower(loan)).thenReturn(0);
        assertLoanMetricError(loan, "Loan don't have termYears or termMonths");
    }

    @Test
    void calculateLoanMetricWithValidLoanCalculatesLoanMetric() {

        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setAnnualInterest(BigDecimal.ONE);
        loan.setRequestedAmount(BigDecimal.TEN);
        when(loanMetricUtils.getExponentPower(loan)).thenReturn(1);
        when(loanMetricUtils.getMonthlyInterestRate(loan.getAnnualInterest())).thenReturn(BigDecimal.ONE);
        when(loanMetricUtils.getConsumerNumerator(loan.getRequestedAmount(), BigDecimal.ONE)).thenReturn(BigDecimal.TEN);
        when(loanMetricUtils.getDenominator(BigDecimal.ONE, 1)).thenReturn(BigDecimal.ONE);

        LoanMetric result = consumerLoanMetricCalculator.calculateLoanMetric(loan);

        assertFalse(result.isError());
        assertEquals(1L, result.getLoanId());
        assertEquals("consumer", result.getLoanType());
        assertEquals(0, BigDecimal.ONE.compareTo(result.getMonthlyRate()));
        assertEquals(0, BigDecimal.TEN.compareTo(result.getMonthlyPayment()));
    }

    private void assertLoanMetricError(Loan loan, String expectedErrorDescription) {
        LoanMetric result = consumerLoanMetricCalculator.calculateLoanMetric(loan);
        assertTrue(result.isError());
        assertEquals(expectedErrorDescription, result.getErrorDescription());
    }


}