package com.lava_jato.services;

import com.lava_jato.entities.dto.PessoaJuridicaDTO;
import com.lava_jato.entities.dto.responses.PessoaJuridicaResponseDTO;
import com.lava_jato.entities.mapstructs.PessoaJuridicaMapper;
import com.lava_jato.entities.model.PessoaJuridica;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.exceptions.handlers.ValidationException;
import com.lava_jato.repositories.ClienteRepository;
import com.lava_jato.repositories.PessoaJuridicaRepository;
import com.lava_jato.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaJuridicaService {

    private final ClienteRepository clienteRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final PessoaJuridicaMapper pessoaJuridicaMapper;

    public  PessoaJuridicaService(ClienteRepository clienteRepository, PessoaJuridicaRepository pessoaJuridicaRepository, PessoaJuridicaMapper pessoaJuridicaMapper) {
        this.clienteRepository = clienteRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaJuridicaMapper = pessoaJuridicaMapper;
    }

    public PessoaJuridicaResponseDTO createPessoaJuridica(PessoaJuridicaDTO pessoaJuridicaDTO) {
        validarCamposObrigatoriosPessoaJuridica(pessoaJuridicaDTO);
        Util.validarEmail(pessoaJuridicaDTO.getEmail());
        verificaSeExisteClientePeloEmail(pessoaJuridicaDTO.getEmail());

        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setNomeEmpresa(pessoaJuridicaDTO.getNomeEmpresa());
        pessoaJuridica.setNomeResponsavel(pessoaJuridicaDTO.getNomeResponsavel());
        pessoaJuridica.setTelefone(pessoaJuridicaDTO.getTelefone());
        pessoaJuridica.setEmail(pessoaJuridicaDTO.getEmail());
        pessoaJuridica.setObservacoes(pessoaJuridicaDTO.getObservacoes());
        pessoaJuridica.setDataCriacao(LocalDate.now());

        PessoaJuridica pessoaJuridicaSalva = pessoaJuridicaRepository.save(pessoaJuridica);
        PessoaJuridicaResponseDTO pessoaJuridicaResponseDTO = pessoaJuridicaMapper.toResponseDTO(pessoaJuridicaSalva);

        return pessoaJuridicaResponseDTO;
    }

    public PessoaJuridicaResponseDTO updatePessoaJuridica(Long clienteId,PessoaJuridicaDTO pessoaJuridicaDTO) {
        PessoaJuridica pessoaJuridica =  findPessoaJuridicaById(clienteId);

        if(pessoaJuridicaDTO.getNomeEmpresa() != null && !pessoaJuridicaDTO.getNomeEmpresa().isEmpty()){
            pessoaJuridica.setNomeEmpresa(pessoaJuridicaDTO.getNomeEmpresa());
        }

        if(pessoaJuridicaDTO.getNomeResponsavel() != null && !pessoaJuridicaDTO.getNomeResponsavel().isEmpty()){
            pessoaJuridica.setNomeResponsavel(pessoaJuridicaDTO.getNomeResponsavel());
        }

        if(pessoaJuridicaDTO.getTelefone() != null && !pessoaJuridicaDTO.getTelefone().isEmpty()){
            pessoaJuridica.setTelefone(pessoaJuridicaDTO.getTelefone());
        }

        if(pessoaJuridicaDTO.getEmail() != null && !pessoaJuridicaDTO.getEmail().isEmpty()){
            pessoaJuridica.setEmail(pessoaJuridicaDTO.getEmail());
        }

        if(pessoaJuridicaDTO.getObservacoes() != null && !pessoaJuridicaDTO.getObservacoes().isEmpty()){
            pessoaJuridica.setObservacoes(pessoaJuridicaDTO.getObservacoes());
        }

        PessoaJuridica pessoaJuridicaAtualizada = pessoaJuridicaRepository.save(pessoaJuridica);
        PessoaJuridicaResponseDTO pessoaJuridicaResponseDTO = pessoaJuridicaMapper.toResponseDTO(pessoaJuridicaAtualizada);

        return pessoaJuridicaResponseDTO;

    }

    public List<PessoaJuridicaResponseDTO> findAllPessoaJuridica() {
        List<PessoaJuridica>  pessoasJuridicas =  pessoaJuridicaRepository.findAll();
        List<PessoaJuridicaResponseDTO> pessoasJuridicasResponse = pessoasJuridicas.stream().map(pessoaJuridicaMapper::toResponseDTO).collect(Collectors.toList());
        return pessoasJuridicasResponse;
    }

    private PessoaJuridica findPessoaJuridicaById(Long clienteId) {
        return pessoaJuridicaRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado pelo id" + clienteId));
    }
    private void verificaSeExisteClientePeloEmail(String email) {
        if(clienteRepository.existsByEmail(email)) {
            throw new BusinessException("Já existe um cliente com este E-mail.");
        }
    }

    private void validarCamposObrigatoriosPessoaJuridica(PessoaJuridicaDTO pessoaJuridicaDTO) {
        List<String> camposObrigatorios = new ArrayList<>();

        if (Util.isNullOrEmpty(pessoaJuridicaDTO.getNomeEmpresa())) {
            camposObrigatorios.add("Nome da Empresa");
        }

        if (Util.isNullOrEmpty(pessoaJuridicaDTO.getNomeResponsavel())) {
            camposObrigatorios.add("Nome do Responsavel");
        }
        if (Util.isNullOrEmpty(pessoaJuridicaDTO.getTelefone())) {
            camposObrigatorios.add("Telefone");
        }
        if (Util.isNullOrEmpty(pessoaJuridicaDTO.getEmail())) {
            camposObrigatorios.add("E-mail");
        }

        if (!camposObrigatorios.isEmpty()) {
            throw new ValidationException("Os seguintes campos são obrigatórios: " + String.join(", ", camposObrigatorios));
        }
    }
}
