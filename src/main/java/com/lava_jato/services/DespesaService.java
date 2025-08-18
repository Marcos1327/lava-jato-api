package com.lava_jato.services;

import com.lava_jato.entities.dto.request.DespesaDTO;
import com.lava_jato.entities.dto.responses.DespesaResponseDTO;
import com.lava_jato.entities.enums.StatusPagamento;
import com.lava_jato.entities.mapstructs.DespesaMapper;
import com.lava_jato.entities.model.caixa.Despesa;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.repositories.DespesaRepository;
import com.lava_jato.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final DespesaMapper despesaMapper;

    public DespesaService(DespesaRepository despesaRepository, DespesaMapper despesaMapper) {
        this.despesaRepository = despesaRepository;
        this.despesaMapper = despesaMapper;
    }

    public DespesaResponseDTO create(DespesaDTO despesaDTO) {
        validarCamposObrigatorios(despesaDTO);

        Despesa despesa = new Despesa();
        despesa.setDescricao(despesaDTO.getDescricao());
        despesa.setValorDespesa(despesaDTO.getValorDespesa());
        despesa.setDataDespesa(despesaDTO.getDataDespesa());
        despesa.setStatusPagamento(despesaDTO.getStatusPagamento());
        despesa.setDataCriacao(LocalDate.now());

        despesaRepository.save(despesa);
        DespesaResponseDTO responseDTO = despesaMapper.toResponseDTO(despesa);

        return responseDTO;
    }

    public DespesaResponseDTO update(Long despesaId, DespesaDTO despesaDTO) {
        Despesa despesa = findById(despesaId);

        if(despesaDTO.getDescricao() != null && !despesaDTO.getDescricao().isEmpty()){
            despesa.setDescricao(despesaDTO.getDescricao());
        }
        if(despesaDTO.getValorDespesa() != null){
            despesa.setValorDespesa(despesaDTO.getValorDespesa());
        }
        if(despesaDTO.getDataDespesa() != null){
            despesa.setDataDespesa(despesaDTO.getDataDespesa());
        }
        if(despesaDTO.getStatusPagamento() != null){
            despesa.setStatusPagamento(despesaDTO.getStatusPagamento());
        }
        despesaRepository.save(despesa);

        return despesaMapper.toResponseDTO(despesa);
    }

    public Page<DespesaResponseDTO> findAll(Pageable pageable) {
        Page<Despesa> despesas = despesaRepository.findAll(pageable);
        return despesas.map(despesaMapper::toResponseDTO);
    }

    public DespesaResponseDTO getById(Long despesaId) {
        Despesa despesa = findById(despesaId);
        return despesaMapper.toResponseDTO(despesa);
    }

    public void deleteById(Long despesaId) {
        Despesa despesa = findById(despesaId);
        despesaRepository.delete(despesa);
    }

    public List<Despesa> findAllDespesaByStatusPagamento(StatusPagamento statusPagamento){
        return despesaRepository.findByStatusPagamento(statusPagamento);
    }

    private Despesa findById(Long despesaId){
        return despesaRepository.findById(despesaId).orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada pelo id: " +  despesaId));
    }
    private void validarCamposObrigatorios(DespesaDTO despesaDTO) {
        Map<String, Object> camposObrigatorios = new HashMap<>();
        camposObrigatorios.put("Descrição", despesaDTO.getDescricao());
        camposObrigatorios.put("Valor Despesa", despesaDTO.getValorDespesa());
        camposObrigatorios.put("Status Pagamento",despesaDTO.getStatusPagamento());
        camposObrigatorios.put("Data da Despesa", despesaDTO.getDataDespesa());

        Util.validarCamposObrigatorios(camposObrigatorios);
    }
}
