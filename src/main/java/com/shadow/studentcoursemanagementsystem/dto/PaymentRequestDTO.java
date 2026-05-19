package com.shadow.studentcoursemanagementsystem.dto;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private Long studentId;
    private double amount;
    private String description;
    private String type;            // "ONLINE" or "CASH"

    // Online payment fields
    private String transactionId;
    private String bankName;

    // Cash payment fields
    private String receivedBy;
    private String receiptNumber;
}
