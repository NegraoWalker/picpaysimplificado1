package com.walker.picpaysimplificado1.respositories;

import com.walker.picpaysimplificado1.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByDocument(String document); //método usado para pesquisar um usuário pelo CNPJ/CPF
    Optional<User> findUserById(Long id); //método usado para pesquisar um usuário por id
}
