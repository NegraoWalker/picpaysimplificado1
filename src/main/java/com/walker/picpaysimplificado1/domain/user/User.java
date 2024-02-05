package com.walker.picpaysimplificado1.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document; //cada usuário só pode cadastrar um cpf/cnpj

    @Column(unique = true)
    private String email; //cada usuário só pode cadastrar um email

    private String password;

    private BigDecimal balance; //saldo do usuário

    private UserType userType; //dois tipos de usuários: comum e lojistas
}
