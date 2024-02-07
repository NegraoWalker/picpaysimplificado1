package com.walker.picpaysimplificado1.domain.transaction;

import com.walker.picpaysimplificado1.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount; //valor da transferência entre users

    @ManyToOne //um user pode ter várias transferências, mas uma transferência só poder ter um user
    @JoinColumn(name = "sender_id")
    private User sender; //user que envia um valor de transferência: sender=payer

    @ManyToOne //um user pode ter várias transferências, mas uma transferência só poder ter um user
    @JoinColumn(name = "receiver_id")
    private User receiver; //user que recebe um valor de transferência: receiver=payee

    private LocalDateTime timestamp; //data e tempo que foi realizada a transferência

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
