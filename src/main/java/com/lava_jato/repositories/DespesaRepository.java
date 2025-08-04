package com.lava_jato.repositories;

import com.lava_jato.entities.model.caixa.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
