package com.spring.ayi.app.repository;

import com.spring.ayi.app.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMarkerRepository extends JpaRepository<Marker, Long> {

    List<Marker> findAllBySoftDeleteIsFalse();
}
