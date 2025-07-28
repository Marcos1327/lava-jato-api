package com.lava_jato.services;

import com.lava_jato.entities.dto.PessoaFisicaDTO;
import com.lava_jato.entities.model.PessoaFisica;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ValidationException;
import com.lava_jato.repositories.ClienteRepository;
import com.lava_jato.repositories.PessoaFisicaRepository;
import com.lava_jato.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaFisicaService {

    private final ClienteRepository clienteRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    public  PessoaFisicaService(ClienteRepository clienteRepository, PessoaFisicaRepository pessoaFisicaRepository) {
        this.clienteRepository = clienteRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    public PessoaFisica createPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) {
        validarCamposObrigatoriosPessoaFisica(pessoaFisicaDTO);
        Util.validarEmail(pessoaFisicaDTO.getEmail());
        verificaSeExisteClientePeloEmail(pessoaFisicaDTO.getEmail());

        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setNome(pessoaFisicaDTO.getNome());
        pessoaFisica.setTelefone(pessoaFisicaDTO.getTelefone());
        pessoaFisica.setEmail(pessoaFisicaDTO.getEmail());
        pessoaFisica.setObservacoes(pessoaFisicaDTO.getObservacoes());
        pessoaFisica.setDataCriacao(LocalDate.now());

        return clienteRepository.save(pessoaFisica);
    }

    public List<PessoaFisica> findAllPessoaFisica(){
        return pessoaFisicaRepository.findAll();
    }

    private void verificaSeExisteClientePeloEmail(String email) {
        if(clienteRepository.existsByEmail(email)) {
            throw new BusinessException("Já existe um cliente com este E-mail.");
        }
    }

    private void validarCamposObrigatoriosPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) {
        List<String> camposObrigatorios = new ArrayList<>();

        if (Util.isNullOrEmpty(pessoaFisicaDTO.getNome())) {
            camposObrigatorios.add("Nome");
        }
        if (Util.isNullOrEmpty(pessoaFisicaDTO.getTelefone())) {
            camposObrigatorios.add("Telefone");
        }
        if (Util.isNullOrEmpty(pessoaFisicaDTO.getEmail())) {
            camposObrigatorios.add("E-mail");
        }

        if (!camposObrigatorios.isEmpty()) {
            throw new ValidationException("Os seguintes campos são obrigatórios: " + String.join(", ", camposObrigatorios));
        }
    }
}
