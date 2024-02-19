package com.walker.picpaysimplificado1.controllers;

import com.walker.picpaysimplificado1.domain.transaction.Transaction;
import com.walker.picpaysimplificado1.dtos.TransactionDto;
import com.walker.picpaysimplificado1.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto){
      Transaction newTransaction = transactionService.createTransaction(transactionDto);
      return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
