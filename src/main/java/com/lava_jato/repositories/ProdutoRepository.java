package com.lava_jato.repositories;

import com.lava_jato.entities.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsProdutoByNomeProduto(String nomeProduto);
}
