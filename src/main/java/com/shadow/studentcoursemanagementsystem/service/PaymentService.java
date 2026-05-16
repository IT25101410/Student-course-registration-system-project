package com.shadow.studentcoursemanagementsystem.service;




<<<<<<< HEAD:src/main/java/com/sliit/scrs/service/PaymentService.java
import com.sliit.scrs.dto.PaymentRequestDTO;
import com.sliit.scrs.dto.PaymentResponseDTO;
import com.sliit.scrs.dto.UpdatePaymentStatusDTO;
=======

import com.shadow.studentcoursemanagementsystem.dto.PaymentRequestDTO;
import com.shadow.studentcoursemanagementsystem.dto.PaymentResponseDTO;
import com.shadow.studentcoursemanagementsystem.dto.UpdatePaymentStatusDTO;
>>>>>>> de8a110 (Change name of Package):src/main/java/com/shadow/studentcoursemanagementsystem/service/PaymentService.java

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
