package com.sliit.scrs.dto;



import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private double amount;
    private LocalDate paymentDate;
    private String status;
    private String description;
    private String type;
    private String paymentSummary;  // result of processPayment()
}
