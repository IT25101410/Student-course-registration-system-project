package com.sliit.scrs.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentStatusDTO {
    private String status; // "PENDING", "COMPLETED", "FAILED"
}
