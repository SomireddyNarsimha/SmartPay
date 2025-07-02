package com.Project.smartpay.controller;

import com.Project.smartpay.model.Payment;
import com.Project.smartpay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Enable if frontend is on another port
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Add new payment
    @PostMapping("/payment")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    // Get current year monthly summary
    @GetMapping("/current-year-summary")
    public Map<Month, Double> getCurrentYearSummary() {
        return paymentService.getCurrentYearSummary();
    }
}

