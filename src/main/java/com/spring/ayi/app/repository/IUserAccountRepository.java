package com.spring.ayi.app.repository;

import com.spring.ayi.app.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByMail(String mailAccount);

    Boolean existsByMail(String mail);
}
