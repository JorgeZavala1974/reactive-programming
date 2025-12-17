package com.david.reactiveprogramming.repository;

import com.david.reactiveprogramming.domain.Persona;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends R2dbcRepository<Persona,Long> {
}
