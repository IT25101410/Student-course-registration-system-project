package com.sliit.scrs.service;




import com.sliit.scrs.dto.PaymentRequestDTO;
import com.sliit.scrs.dto.PaymentResponseDTO;
import com.sliit.scrs.dto.UpdatePaymentStatusDTO;

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
