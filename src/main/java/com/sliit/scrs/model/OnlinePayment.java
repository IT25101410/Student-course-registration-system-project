package com.sliit.scrs.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("ONLINE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OnlinePayment extends Payment {

    private String transactionId;
    private String bankName;

    public OnlinePayment(Long id, Student student, double amount,
                         java.time.LocalDate paymentDate, String status,
                         String description, String transactionId,
                         String bankName) {
        super(id, student, amount, paymentDate, status, description);
        this.transactionId = transactionId;
        this.bankName = bankName;
    }

    @Override
    public String processPayment() {
        return "Online payment of LKR " + getAmount()
                + " processed via " + bankName
                + " | Transaction ID: " + transactionId;
    }
}
