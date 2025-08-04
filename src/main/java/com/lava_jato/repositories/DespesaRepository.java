package com.lava_jato.repositories;

import com.lava_jato.entities.enums.StatusPagamento;
import com.lava_jato.entities.model.caixa.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByStatusPagamento(StatusPagamento statusPagamento);
}
