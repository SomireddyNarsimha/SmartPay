package com.Project.smartpay.repository;

import com.Project.smartpay.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
