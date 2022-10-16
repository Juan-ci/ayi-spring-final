package com.spring.ayi.app.repository;

import com.spring.ayi.app.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMarkerRepository extends JpaRepository<Marker, Long> {

    Optional<Marker> findByMail(String mailAccount);

    Boolean existsByMail(String mail);
}
