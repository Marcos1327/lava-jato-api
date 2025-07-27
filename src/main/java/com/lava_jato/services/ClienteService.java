package com.lava_jato.services;

import com.lava_jato.entities.dto.PessoaFisicaDTO;
import com.lava_jato.entities.model.PessoaFisica;
import com.lava_jato.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public PessoaFisica create(PessoaFisicaDTO pessoaFisicaDTO) {
        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setNome(pessoaFisicaDTO.getNome());
        pessoaFisica.setTelefone(pessoaFisicaDTO.getTelefone());
        pessoaFisica.setEmail(pessoaFisicaDTO.getEmail());
        pessoaFisica.setObservacoes(pessoaFisicaDTO.getObservacoes());
        pessoaFisica.setDataCriacao(LocalDate.now());

        return clienteRepository.save(pessoaFisica);
    }

}
