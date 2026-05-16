package com.shadow.studentcoursemanagementsystem.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("CASH")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CashPayment extends Payment {

    private String receivedBy;   // staff name who received cash
    private String receiptNumber;

    public CashPayment(Long id, Student student, double amount,
                       java.time.LocalDate paymentDate, String status,
                       String description, String receivedBy,
                       String receiptNumber) {
        super(id, student, amount, paymentDate, status, description);
        this.receivedBy = receivedBy;
        this.receiptNumber = receiptNumber;
    }

    @Override
    public String processPayment() {
        return "Cash payment of LKR " + getAmount()
                + " received by " + receivedBy
                + " | Receipt No: " + receiptNumber;
    }
}
