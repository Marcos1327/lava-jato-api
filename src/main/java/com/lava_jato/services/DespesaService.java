package com.lava_jato.services;

import com.lava_jato.entities.dto.request.DespesaDTO;
import com.lava_jato.entities.dto.request.VeiculoDTO;
import com.lava_jato.entities.dto.responses.DespesaResponseDTO;
import com.lava_jato.entities.mapstructs.DespesaMapper;
import com.lava_jato.entities.model.caixa.Despesa;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.exceptions.handlers.ValidationException;
import com.lava_jato.repositories.DespesaRepository;
import com.lava_jato.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final DespesaMapper despesaMapper;

    public DespesaService(DespesaRepository despesaRepository, DespesaMapper despesaMapper) {
        this.despesaRepository = despesaRepository;
        this.despesaMapper = despesaMapper;
    }

    public DespesaResponseDTO create(DespesaDTO despesaDTO) {
        validarCamposObrigatoriosDespesa(despesaDTO);

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

    public DespesaResponseDTO getById(Long despesaId) {
        Despesa despesa = findById(despesaId);
        return despesaMapper.toResponseDTO(despesa);
    }

    private Despesa findById(Long despesaId){
        return despesaRepository.findById(despesaId).orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada pelo id: " +  despesaId));
    }

    private void validarCamposObrigatoriosDespesa(DespesaDTO despesaDTO) {
        List<String> camposObrigatorios = new ArrayList<>();

        if (Util.isNullOrEmpty(despesaDTO.getDescricao())) {
            camposObrigatorios.add("Descrição");
        }
        if (Util.isNullOrEmpty(despesaDTO.getValorDespesa())) {
            camposObrigatorios.add("Valor Despesa");
        }
        if (Util.isNullOrEmpty(despesaDTO.getDataDespesa())) {
            camposObrigatorios.add("Data Despesa");
        }
        if (Util.isNullOrEmpty(despesaDTO.getStatusPagamento())) {
            camposObrigatorios.add("Status Pagamento");
        }
        if (!camposObrigatorios.isEmpty()) {
            throw new ValidationException("Os seguintes campos são obrigatórios: " + String.join(", ", camposObrigatorios));
        }
    }
}
