package com.walker.picpaysimplificado1.services;

import com.walker.picpaysimplificado1.domain.transaction.Transaction;
import com.walker.picpaysimplificado1.domain.user.User;
import com.walker.picpaysimplificado1.dtos.TransactionDto;
import com.walker.picpaysimplificado1.respositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    private  UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate; //será usado para consumir o serviço autorizador externo https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc


    public void createTransaction(TransactionDto transactionDto){
        User sender = this.userService.findUserById(transactionDto.senderId()); //sender=payer
        User receiver = this.userService.findUserById(transactionDto.receiverId()); //receiver=payee

        userService.validateTransaction(sender,transactionDto.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transactionDto.value());

        if (!isAuthorized){
            throw new RuntimeException("Transação não autorizada!");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDto.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value){ //Antes de finalizar a transferência, deve-se consultar um serviço autorizador externo, use este mock para simular (https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc).
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK && Objects.requireNonNull(authorizationResponse.getBody()).get("message") == "Autorizado"){
            return true;
        } else {
            return false;
        }
    }
}
