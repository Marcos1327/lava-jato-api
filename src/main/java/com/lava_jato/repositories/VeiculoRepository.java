package com.lava_jato.repositories;

import com.lava_jato.entities.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
}
