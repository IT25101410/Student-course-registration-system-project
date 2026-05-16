package com.shadow.studentcoursemanagementsystem.controller;

import com.shadow.studentcoursemanagementsystem.dto.*;
import com.shadow.studentcoursemanagementsystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Make a payment
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> makePayment(
            @RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.ok(paymentService.makePayment(dto));
    }

    // View all payments
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // View one payment
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(
            @PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    // View payment history by studen
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<PaymentResponseDTO>> getByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(paymentService.getPaymentsByStudent(studentId));
    }

    // View payments by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentResponseDTO>> getByStatus(
            @PathVariable String status) {
        return ResponseEntity.ok(paymentService.getPaymentsByStatus(status));
    }

    // Update payment status
    @PatchMapping("/{id}/status")
    public ResponseEntity<PaymentResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdatePaymentStatusDTO dto) {
        return ResponseEntity.ok(paymentService.updatePaymentStatus(id, dto));
    }

    // Delete payment record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Payment record deleted successfully.");
    }
}
