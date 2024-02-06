package com.walker.picpaysimplificado1.services;

import com.walker.picpaysimplificado1.domain.user.User;
import com.walker.picpaysimplificado1.domain.user.UserType;
import com.walker.picpaysimplificado1.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount){ //método que valida se uma transferência pode ser realizada
        if (sender.getUserType() == UserType.MERCHANT){
            throw new RuntimeException("Usuário do tipo lojista não está autorizado a realizar transferências!!");
        }

        if (sender.getBalance().compareTo(amount) <= 0){
            throw new RuntimeException("Usuário não tem saldo suficiente para realizar a transferência!!");
        }
    }

    public User findUserById(Long id){
        return this.userRepository.findUserById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado!"));
    }

    public void saveUser(User user){ //método para salvar um usuário no banco de dados
        this.userRepository.save(user);
    }



}
