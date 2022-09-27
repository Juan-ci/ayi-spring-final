package com.spring.ayi.app.repository;

import com.spring.ayi.app.entity.ClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientDetailRepository extends JpaRepository<ClientDetail, Long> {
}
