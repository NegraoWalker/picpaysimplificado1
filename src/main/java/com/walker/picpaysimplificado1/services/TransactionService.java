package com.walker.picpaysimplificado1.services;

import com.walker.picpaysimplificado1.domain.user.User;
import com.walker.picpaysimplificado1.dtos.TransactionDto;
import com.walker.picpaysimplificado1.respositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private  UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;


    public void createTransaction(TransactionDto transactionDto){
        User sender = this.userService.findUserById(transactionDto.senderId()); //sender=payer
        User receiver = this.userService.findUserById(transactionDto.receiverId()); //receiver=payee

        userService.validateTransaction(sender,transactionDto.value());
    }
}
