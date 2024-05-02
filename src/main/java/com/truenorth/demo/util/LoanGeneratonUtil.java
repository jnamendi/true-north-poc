package com.truenorth.demo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truenorth.demo.model.entity.Loan;

@Component
public class LoanGeneratonUtil {
    
    public static List<Loan> loanDatabase = fetchLoans();
    
    private static List<Loan> fetchLoans() {
        Resource resource = new ClassPathResource("data/loans.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inJson = resource.getInputStream();
            List<Loan> loans = mapper.readValue(inJson, new TypeReference<List<Loan>>() {
            });
            return loans;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return new ArrayList<>();
    }
}
