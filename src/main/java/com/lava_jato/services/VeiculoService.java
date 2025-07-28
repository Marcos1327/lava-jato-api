package com.lava_jato.services;

import com.lava_jato.entities.dto.PessoaFisicaDTO;
import com.lava_jato.entities.dto.VeiculoDTO;
import com.lava_jato.entities.model.Veiculo;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.exceptions.handlers.ValidationException;
import com.lava_jato.repositories.ClienteRepository;
import com.lava_jato.repositories.VeiculoRepository;
import com.lava_jato.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteService clienteService;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteService clienteService) {
        this.veiculoRepository = veiculoRepository;
        this.clienteService = clienteService;
    }

    public Veiculo createVeiculo(VeiculoDTO veiculoDTO){
        validarCamposObrigatoriosPessoaFisica(veiculoDTO);
        verificaSeExisteVeiculoPelaPlaca(veiculoDTO.getPlaca());

        Veiculo veiculo = new Veiculo();

        veiculo.setProprietario(clienteService.getClienteById(veiculoDTO.getProprietarioId()));
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setObservacao(veiculoDTO.getObservacao());
        veiculo.setDataCriacao(LocalDate.now());

        return veiculoRepository.save(veiculo);
    }

    public Veiculo updateVeiculo(Long veiculoId, VeiculoDTO veiculoDTO){
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
            veiculo.setProprietario(clienteService.getClienteById(veiculoDTO.getProprietarioId()));
        }

        veiculoRepository.save(veiculo);
        return veiculo;
    }

    public List<Veiculo> findAllVeiculos(){
        return veiculoRepository.findAll();
    }

    public Veiculo getById(long veiculoId){
        return findById(veiculoId);
    }

    public void deleteById(long veiculoId){
        Veiculo veiculo = findById(veiculoId);
        veiculoRepository.delete(veiculo);
    }

    private void verificaSeExisteVeiculoPelaPlaca(String placa){
        if(veiculoRepository.existsVeiculoByPlaca(placa)) {
            throw new BusinessException("Já existe um veiculo com este Placa.");
        }
    }
    private Veiculo findById(Long veiculoId){
        return veiculoRepository.findById(veiculoId).orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrato pelo id: " + veiculoId));
    }
    private void validarCamposObrigatoriosPessoaFisica(VeiculoDTO veiculoDTO) {
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
