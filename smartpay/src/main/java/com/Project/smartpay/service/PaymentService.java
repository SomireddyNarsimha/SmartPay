package com.Project.smartpay.service;

import com.Project.smartpay.model.Payment;
import com.Project.smartpay.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Save payment
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Get current year monthly summary
    public Map<Month, Double> getCurrentYearSummary() {
        int year = LocalDate.now().getYear();
        LocalDateTime start = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, 12, 31, 23, 59);

        List<Payment> currentYearPayments = paymentRepository.findByTimestampBetween(start, end);

        Map<Month, Double> monthlyTotals = new HashMap<>();

        // Initialize months with 0.0
        for (Month month : Month.values()) {
            monthlyTotals.put(month, 0.0);
        }

        for (Payment p : currentYearPayments) {
            Month month = p.getTimestamp().getMonth();
            monthlyTotals.merge(month, p.getAmount(), Double::sum);
        }

        return monthlyTotals;
    }
}

