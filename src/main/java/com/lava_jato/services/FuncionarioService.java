package com.lava_jato.services;

import com.lava_jato.entities.dto.request.FuncionarioDTO;
import com.lava_jato.entities.dto.responses.FuncionarioResponseDTO;
import com.lava_jato.entities.mapstructs.FuncionarioMapper;
import com.lava_jato.entities.model.Funcionario;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.repositories.FuncionarioRepository;
import com.lava_jato.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    @Transactional
    public FuncionarioResponseDTO create(FuncionarioDTO funcionarioDTO) {
        validarCamposObrigatorios(funcionarioDTO);
        verificarSeExisteFuncionarioPeloNomeETelefone(funcionarioDTO.getNome(), funcionarioDTO.getTelefone());

        Funcionario funcionario = new Funcionario();

        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCargo(funcionarioDTO.getCargo());
        funcionario.setTelefone(funcionarioDTO.getTelefone());
        funcionario.setObservacoes(funcionarioDTO.getObservacoes());
        funcionario.setIsTemporario(funcionarioDTO.getIsTemporario());
        funcionario.setVinculo(definirVinculo(funcionario));
        funcionario.setDataCriacao(LocalDate.now());

        funcionarioRepository.save(funcionario);
        FuncionarioResponseDTO funcionarioResponseDTO = funcionarioMapper.toResponseDTO(funcionario);

        return funcionarioResponseDTO;
    }

    @Transactional
    public FuncionarioResponseDTO update(Long funcionarioId, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = findById(funcionarioId);

        if(funcionarioDTO.getNome() != null && !funcionarioDTO.getNome().isEmpty()) {
            funcionario.setNome(funcionarioDTO.getNome());
        }
        if(funcionarioDTO.getCargo() != null && !funcionarioDTO.getCargo().isEmpty()) {
            funcionario.setCargo(funcionarioDTO.getCargo());
        }
        if(funcionarioDTO.getTelefone() != null && !funcionarioDTO.getTelefone().isEmpty()) {
            funcionario.setTelefone(funcionarioDTO.getTelefone());
        }
        if(funcionarioDTO.getObservacoes() != null && !funcionarioDTO.getObservacoes().isEmpty()) {
            funcionario.setObservacoes(funcionarioDTO.getObservacoes());
        }
        if(funcionarioDTO.getIsTemporario() != null) {
            funcionario.setIsTemporario(funcionarioDTO.getIsTemporario());
            funcionario.setVinculo(definirVinculo(funcionario));
        }

        funcionarioRepository.save(funcionario);
        FuncionarioResponseDTO responseDTO = funcionarioMapper.toResponseDTO(funcionario);

        return responseDTO;
    }

    public Page<FuncionarioResponseDTO> findAll(Pageable pageable){
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        return funcionarios.map(funcionarioMapper::toResponseDTO);
    }

    public FuncionarioResponseDTO getById(Long funcionarioId) {
        Funcionario funcionario = findById(funcionarioId);
        return funcionarioMapper.toResponseDTO(funcionario);
    }

    @Transactional
    public void deleteById(Long funcionarioId){
        Funcionario funcionario = findById(funcionarioId);
        funcionarioRepository.delete(funcionario);
    }

    private String definirVinculo(Funcionario funcionario) {
        return funcionario.getIsTemporario() ? "Freelancer" : "Efetivo";
    }
    private Funcionario findById (Long funcionarioId ) {
       return funcionarioRepository.findById(funcionarioId).orElseThrow(() ->
                new ResourceNotFoundException("Funcionário não encontrado pelo Id : " + funcionarioId));
    }
    private void validarCamposObrigatorios(FuncionarioDTO funcionarioDTO) {
        Map<String, Object> camposObrigatorios = new HashMap<>();
        camposObrigatorios.put("Nome", funcionarioDTO.getNome());
        camposObrigatorios.put("Telefone",  funcionarioDTO.getTelefone());
        camposObrigatorios.put("Função", funcionarioDTO.getCargo());
        camposObrigatorios.put("Vínculo", funcionarioDTO.getIsTemporario());

        Util.validarCamposObrigatorios(camposObrigatorios);
    }
    private void verificarSeExisteFuncionarioPeloNomeETelefone(String nome, String telefone){
        if(funcionarioRepository.existsFuncionarioByNomeAndTelefone(nome, telefone)){
            throw new BusinessException("Ja existe um funcionario cadastrado com esse nome e telefone");
        }
    }

}
