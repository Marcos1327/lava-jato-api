package com.lava_jato.repositories;

import com.lava_jato.entities.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsFuncionarioByNomeAndTelefone(String nome, String telefone);
}
