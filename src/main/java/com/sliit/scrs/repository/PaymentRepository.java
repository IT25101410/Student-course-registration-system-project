package com.sliit.scrs.repository;

import com.sliit.scrs.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentId(Long studentId);
    List<Payment> findByStatus(String status);
    List<Payment> findByStudentIdAndStatus(Long studentId, String status);
}
