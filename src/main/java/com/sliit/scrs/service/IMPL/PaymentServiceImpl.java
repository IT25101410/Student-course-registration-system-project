package com.sliit.scrs.service.IMPL;


import com.sliit.scrs.dto.*;
import com.sliit.scrs.model.*;
import com.sliit.scrs.repository.*;
import com.sliit.scrs.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public PaymentResponseDTO makePayment(PaymentRequestDTO dto) {

        // Validate student exists first
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException(
                        "Student not found with id: " + dto.getStudentId()
                ));

        Payment payment;

        // Inheritance + Polymorphism: create correct payment subtype

        if ("ONLINE".equalsIgnoreCase(dto.getType())) {

            payment = new OnlinePayment(
                    null,
                    student,
                    dto.getAmount(),
                    LocalDate.now(),
                    "COMPLETED",
                    dto.getDescription(),
                    dto.getTransactionId(),
                    dto.getBankName()
            );

        } else if ("CASH".equalsIgnoreCase(dto.getType())) {

            payment = new CashPayment(
                    null,
                    student,
                    dto.getAmount(),
                    LocalDate.now(),
                    "COMPLETED",
                    dto.getDescription(),
                    dto.getReceivedBy(),
                    dto.getReceiptNumber()
            );

        } else {
            throw new RuntimeException("Invalid payment type. Use CASH or ONLINE.");
        }

        Payment saved = paymentRepository.save(payment);
        return mapToResponse(saved);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Payment not found with id: " + id));
        return mapToResponse(payment);
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByStudent(Long studentId) {
        return paymentRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByStatus(String status) {
        return paymentRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO updatePaymentStatus(Long id,
                                                  UpdatePaymentStatusDTO dto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Payment not found with id: " + id));

        payment.setStatus(dto.getStatus());
        Payment updated = paymentRepository.save(payment);
        return mapToResponse(updated);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    private PaymentResponseDTO mapToResponse(Payment payment) {
        String type = (payment instanceof OnlinePayment)
                ? "ONLINE" : "CASH";

        return new PaymentResponseDTO(
                payment.getId(),
                payment.getStudent().getId(),
                payment.getStudent().getName(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getStatus(),
                payment.getDescription(),
                type,
                payment.processPayment() // Polymorphism in action
        );
    }
}
