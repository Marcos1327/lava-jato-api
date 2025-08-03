package com.lava_jato.repositories;

import com.lava_jato.entities.model.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {
    boolean existsTipoServicoByNomeServico(String nomeServico);
}
