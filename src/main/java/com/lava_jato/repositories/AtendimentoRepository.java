package com.lava_jato.repositories;

import com.lava_jato.entities.enums.StatusAtendimento;
import com.lava_jato.entities.enums.StatusPagamento;
import com.lava_jato.entities.model.atendimento.Atendimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findAllByArquivadoTrue();
    Page<Atendimento> findAllByArquivadoTrue(Pageable pageable);
    List<Atendimento> findAllByStatusAtendimentoAndArquivadoFalseAndStatusPagamento(StatusAtendimento statusAtendimento, StatusPagamento statusPagamento);
    List<Atendimento> findByStatusPagamento(StatusPagamento statusPagamento);
}
