package org.example.repository;

import org.example.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitorRepository extends JpaRepository<Alumno, Long> {
}
