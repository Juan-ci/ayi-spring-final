package com.spring.ayi.app.repository;

import com.spring.ayi.app.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByDocumentNumber(String documentNumber) throws NoSuchElementException;

    Boolean existsByDocumentNumber(String documentNumber);
}
