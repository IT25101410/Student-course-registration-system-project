package com.shadow.studentcoursemanagementsystem.service;





import com.shadow.studentcoursemanagementsystem.dto.PaymentRequestDTO;
import com.shadow.studentcoursemanagementsystem.dto.PaymentResponseDTO;
import com.shadow.studentcoursemanagementsystem.dto.UpdatePaymentStatusDTO;

import java.util.List;

public interface PaymentService {
    PaymentResponseDTO makePayment(PaymentRequestDTO dto);
    List<PaymentResponseDTO> getAllPayments();
    PaymentResponseDTO getPaymentById(Long id);
    List<PaymentResponseDTO> getPaymentsByStudent(Long studentId);
    List<PaymentResponseDTO> getPaymentsByStatus(String status);
    PaymentResponseDTO updatePaymentStatus(Long id, UpdatePaymentStatusDTO dto);
    void deletePayment(Long id);
}
