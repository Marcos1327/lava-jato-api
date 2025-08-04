package com.lava_jato.repositories;

import com.lava_jato.entities.enums.StatusAtendimento;
import com.lava_jato.entities.enums.StatusPagamento;
import com.lava_jato.entities.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findAllByArquivadoTrue();
    List<Atendimento> findAllByStatusAtendimentoAndArquivadoFalseAndStatusPagamento(StatusAtendimento statusAtendimento, StatusPagamento statusPagamento);
}
