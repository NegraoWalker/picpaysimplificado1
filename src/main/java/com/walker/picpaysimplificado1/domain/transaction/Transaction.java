package com.walker.picpaysimplificado1.domain.transaction;

import com.walker.picpaysimplificado1.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
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
}
