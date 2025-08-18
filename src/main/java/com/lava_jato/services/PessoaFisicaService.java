package com.lava_jato.services;

import com.lava_jato.entities.dto.request.PessoaFisicaDTO;
import com.lava_jato.entities.dto.responses.PessoaFisicaResponseDTO;
import com.lava_jato.entities.mapstructs.PessoaFisicaMapper;
import com.lava_jato.entities.model.PessoaFisica;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.exceptions.handlers.ValidationException;
import com.lava_jato.repositories.ClienteRepository;
import com.lava_jato.repositories.PessoaFisicaRepository;
import com.lava_jato.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PessoaFisicaService {

    private final ClienteRepository clienteRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaFisicaMapper pessoaFisicaMapper;

    public  PessoaFisicaService(ClienteRepository clienteRepository, PessoaFisicaRepository pessoaFisicaRepository, PessoaFisicaMapper pessoaFisicaMapper) {
        this.clienteRepository = clienteRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaFisicaMapper = pessoaFisicaMapper;
    }

    @Transactional
    public PessoaFisicaResponseDTO createPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) {
        validarCamposObrigatorios(pessoaFisicaDTO);
        Util.validarEmail(pessoaFisicaDTO.getEmail());
        verificaSeExisteClientePeloEmail(pessoaFisicaDTO.getEmail());

        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setNome(pessoaFisicaDTO.getNome());
        pessoaFisica.setTelefone(pessoaFisicaDTO.getTelefone());
        pessoaFisica.setEmail(pessoaFisicaDTO.getEmail());
        pessoaFisica.setObservacoes(pessoaFisicaDTO.getObservacoes());
        pessoaFisica.setDataCriacao(LocalDate.now());

        pessoaFisicaRepository.save(pessoaFisica);
        PessoaFisicaResponseDTO pessoaFisicaResponse = pessoaFisicaMapper.toResponseDTO(pessoaFisica);

        return pessoaFisicaResponse;
    }

    @Transactional
    public PessoaFisicaResponseDTO updatePessoaFisica(Long clienteId, PessoaFisicaDTO pessoaFisicaDTO) {
        PessoaFisica pessoaFisica =  findPessoaFisicaById(clienteId);

        if(pessoaFisicaDTO.getNome() != null && !pessoaFisicaDTO.getNome().isEmpty()){
            pessoaFisica.setNome(pessoaFisicaDTO.getNome());
        }

        if(pessoaFisicaDTO.getTelefone() != null && !pessoaFisicaDTO.getTelefone().isEmpty()){
            pessoaFisicaDTO.setTelefone(pessoaFisicaDTO.getTelefone());
        }

        if(pessoaFisicaDTO.getEmail() != null && !pessoaFisicaDTO.getEmail().isEmpty()){
            pessoaFisica.setEmail(pessoaFisicaDTO.getEmail());
        }

        if(pessoaFisicaDTO.getObservacoes() != null && !pessoaFisicaDTO.getObservacoes().isEmpty()){
            pessoaFisica.setObservacoes(pessoaFisicaDTO.getObservacoes());
        }

        pessoaFisicaRepository.save(pessoaFisica);
        PessoaFisicaResponseDTO pessoaFisicaResponse = pessoaFisicaMapper.toResponseDTO(pessoaFisica);
        return pessoaFisicaResponse;
    }

    public List<PessoaFisicaResponseDTO> findAllPessoaFisica(){
        List<PessoaFisica> pessoasFisicas = pessoaFisicaRepository.findAll();
        List<PessoaFisicaResponseDTO> pessoasFisicasFisicasList = pessoasFisicas.stream().map(pessoaFisicaMapper::toResponseDTO).collect(Collectors.toList());
        return pessoasFisicasFisicasList;
    }

    private PessoaFisica findPessoaFisicaById(Long clienteId){
        return pessoaFisicaRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado pelo id: " + clienteId));
    }
    private void verificaSeExisteClientePeloEmail(String email) {
        if(clienteRepository.existsByEmail(email)) {
            throw new BusinessException("Já existe um cliente com este E-mail.");
        }
    }
    private void validarCamposObrigatorios(PessoaFisicaDTO pessoaFisicaDTO) {
        Map<String, Object> camposObrigatorios = new HashMap<>();
        camposObrigatorios.put("Nome", pessoaFisicaDTO.getNome());
        camposObrigatorios.put("Telefone", pessoaFisicaDTO.getTelefone());
        camposObrigatorios.put("Email", pessoaFisicaDTO.getEmail());

        Util.validarCamposObrigatorios(camposObrigatorios);
    }
}
