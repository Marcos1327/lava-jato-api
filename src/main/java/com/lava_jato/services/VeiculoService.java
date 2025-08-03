package com.lava_jato.services;

import com.lava_jato.entities.dto.request.VeiculoDTO;
import com.lava_jato.entities.dto.responses.VeiculoResponseDTO;
import com.lava_jato.entities.mapstructs.VeiculoMapper;
import com.lava_jato.entities.model.Veiculo;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.exceptions.handlers.ValidationException;
import com.lava_jato.repositories.VeiculoRepository;
import com.lava_jato.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteService clienteService;
    private final VeiculoMapper veiculoMapper;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteService clienteService, VeiculoMapper veiculoMapper) {
        this.veiculoRepository = veiculoRepository;
        this.clienteService = clienteService;
        this.veiculoMapper = veiculoMapper;
    }

    @Transactional
    public VeiculoResponseDTO createVeiculo(VeiculoDTO veiculoDTO){
        validarCamposObrigatoriosVeiculo(veiculoDTO);
        verificaSeExisteVeiculoPelaPlaca(veiculoDTO.getPlaca());

        Veiculo veiculo = new Veiculo();

        veiculo.setProprietario(clienteService.getClienteByIdEntity(veiculoDTO.getProprietarioId()));
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setObservacao(veiculoDTO.getObservacao());
        veiculo.setDataCriacao(LocalDate.now());

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        VeiculoResponseDTO veiculoResponseDTO = veiculoMapper.toResponseDTO(veiculoSalvo);

        return veiculoResponseDTO;
    }

    @Transactional
    public VeiculoResponseDTO updateVeiculo(Long veiculoId, VeiculoDTO veiculoDTO){
        Veiculo veiculo = findById(veiculoId);

        if(veiculoDTO.getModelo() != null && !veiculo.getModelo().isEmpty()){
            veiculo.setModelo(veiculoDTO.getModelo());
        }

        if(veiculoDTO.getPlaca() != null && !veiculo.getPlaca().isEmpty()){
            veiculo.setPlaca(veiculoDTO.getPlaca());
        }

        if(veiculoDTO.getObservacao() != null && !veiculo.getObservacao().isEmpty()){
            veiculo.setObservacao(veiculo.getObservacao());
        }

        if(veiculoDTO.getProprietarioId() != null){
            veiculo.setProprietario(clienteService.getClienteByIdEntity(veiculoDTO.getProprietarioId()));
        }

        Veiculo veiculoAtualizado = veiculoRepository.save(veiculo);
        VeiculoResponseDTO veiculoResponseDTO = veiculoMapper.toResponseDTO(veiculoAtualizado);
        return veiculoResponseDTO;
    }

    public List<VeiculoResponseDTO> findAllVeiculos(){
        List<Veiculo> veiculos = veiculoRepository.findAll();
        List<VeiculoResponseDTO> veiculosResponseDTO = veiculos.stream().map(veiculoMapper::toResponseDTO).collect(Collectors.toList());
        return veiculosResponseDTO;
    }

    public VeiculoResponseDTO getById(long veiculoId){
        Veiculo veiculo = findById(veiculoId);
        return veiculoMapper.toResponseDTO(veiculo);
    }

    @Transactional
    public void deleteById(long veiculoId){
        Veiculo veiculo = findById(veiculoId);
        veiculoRepository.delete(veiculo);
    }

    private void verificaSeExisteVeiculoPelaPlaca(String placa){
        if(veiculoRepository.existsVeiculoByPlaca(placa)) {
            throw new BusinessException("Já existe um veiculo com este Placa.");
        }
    }
    public Veiculo getVeiculoByIdEntity(Long veiculoId){
        return findById(veiculoId);
    }

    private Veiculo findById(Long veiculoId){
        return veiculoRepository.findById(veiculoId).orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrato pelo id: " + veiculoId));
    }
    private void validarCamposObrigatoriosVeiculo(VeiculoDTO veiculoDTO) {
        List<String> camposObrigatorios = new ArrayList<>();

        if (Util.isNullOrEmpty(veiculoDTO.getProprietarioId().toString())) {
            camposObrigatorios.add("Proprietario");
        }
        if (Util.isNullOrEmpty(veiculoDTO.getModelo())) {
            camposObrigatorios.add("Modelo");
        }
        if (Util.isNullOrEmpty(veiculoDTO.getPlaca())) {
            camposObrigatorios.add("Placa");
        }

        if (!camposObrigatorios.isEmpty()) {
            throw new ValidationException("Os seguintes campos são obrigatórios: " + String.join(", ", camposObrigatorios));
        }
    }
}
