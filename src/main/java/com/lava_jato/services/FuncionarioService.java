package com.lava_jato.services;

import com.lava_jato.entities.dto.FuncionarioDTO;
import com.lava_jato.entities.dto.responses.FuncionarioResponseDTO;
import com.lava_jato.entities.mapstructs.FuncionarioMapper;
import com.lava_jato.entities.model.Funcionario;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.exceptions.handlers.ValidationException;
import com.lava_jato.repositories.FuncionarioRepository;
import com.lava_jato.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    public FuncionarioResponseDTO create(FuncionarioDTO funcionarioDTO) {
        validarCamposObrigatoriosFuncionario(funcionarioDTO);
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

    public List<FuncionarioResponseDTO> findAll(){
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(funcionarioMapper::toResponseDTO).collect(Collectors.toList());
    }

    public FuncionarioResponseDTO getById(Long funcionarioId) {
        Funcionario funcionario = findById(funcionarioId);
        return funcionarioMapper.toResponseDTO(funcionario);
    }

    public void deleteById(Long funcionarioId){
        Funcionario funcionario  = findById(funcionarioId);
        funcionarioRepository.delete(funcionario);
    }

    private String definirVinculo(Funcionario funcionario) {
        return funcionario.getIsTemporario() ? "Freelancer" : "Efetivo";
    }
    private Funcionario findById (Long funcionarioId ) {
       return funcionarioRepository.findById(funcionarioId).orElseThrow(() ->
                new ResourceNotFoundException("Funcionário não encontrado pelo Id : " + funcionarioId));
    }
    private void validarCamposObrigatoriosFuncionario(FuncionarioDTO funcionarioDTO) {
        List<String> camposObrigatorios = new ArrayList<>();

        if (Util.isNullOrEmpty(funcionarioDTO.getNome())) {
            camposObrigatorios.add("Nome");
        }
        if (Util.isNullOrEmpty(funcionarioDTO.getTelefone())) {
            camposObrigatorios.add("Telefone");
        }
        if (Util.isNullOrEmpty(funcionarioDTO.getCargo())) {
            camposObrigatorios.add("Função");
        }
        if (Util.isNullOrEmpty(funcionarioDTO.getIsTemporario())) {
            camposObrigatorios.add("Vínculo");
        }

        if (!camposObrigatorios.isEmpty()) {
            throw new ValidationException("Os seguintes campos são obrigatórios: " + String.join(", ", camposObrigatorios));
        }
    }
    private void verificarSeExisteFuncionarioPeloNomeETelefone(String nome, String telefone){
        if(funcionarioRepository.existsFuncionarioByNomeAndTelefone(nome, telefone)){
            throw new BusinessException("Ja existe um funcionario cadastrado com esse nome e telefone");
        }
    }

}
