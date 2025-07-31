package com.lava_jato.services;

import com.lava_jato.entities.dto.TipoServicoDTO;
import com.lava_jato.entities.dto.VeiculoDTO;
import com.lava_jato.entities.dto.responses.TipoServicoResponseDTO;
import com.lava_jato.entities.mapstructs.TipoServicoMapper;
import com.lava_jato.entities.model.TipoServico;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.exceptions.handlers.ValidationException;
import com.lava_jato.repositories.TipoServicoRepository;
import com.lava_jato.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoServicoService {

    private final TipoServicoRepository tipoServicoRepository;
    private final TipoServicoMapper tipoServicoMapper;

    public TipoServicoService(TipoServicoRepository tipoServicoRepository,  TipoServicoMapper tipoServicoMapper) {
        this.tipoServicoRepository = tipoServicoRepository;
        this.tipoServicoMapper = tipoServicoMapper;
    }

    public TipoServicoResponseDTO create(TipoServicoDTO tipoServicoDTO) {
        validarCamposObrigatoriosServico(tipoServicoDTO);
        existTipoServicoByName(tipoServicoDTO.getNomeServico());

        TipoServico servico = new TipoServico();

        servico.setNomeServico(tipoServicoDTO.getNomeServico());
        servico.setPrecoServico(tipoServicoDTO.getPrecoServico());
        servico.setObservacao(tipoServicoDTO.getObservacao());
        servico.setDataCriacao(LocalDate.now());

        tipoServicoRepository.save(servico);

        TipoServicoResponseDTO responseDTO = tipoServicoMapper.toResponseDTO(servico);

        return responseDTO;
    }

    public TipoServicoResponseDTO update (Integer tipoServicoId, TipoServicoDTO tipoServicoDTO) {
        TipoServico tipoServico = findById(tipoServicoId);

        if(tipoServicoDTO.getNomeServico() != null && !tipoServicoDTO.getNomeServico().isEmpty()){
            tipoServico.setNomeServico(tipoServicoDTO.getNomeServico());
        }
        if(tipoServicoDTO.getPrecoServico() != null) {
            tipoServico.setPrecoServico(tipoServicoDTO.getPrecoServico());
        }
        if(tipoServicoDTO.getObservacao() != null &&  !tipoServicoDTO.getObservacao().isEmpty()) {
            tipoServico.setObservacao(tipoServicoDTO.getObservacao());
        }

        tipoServicoRepository.save(tipoServico);
        TipoServicoResponseDTO  responseDTO = tipoServicoMapper.toResponseDTO(tipoServico);

        return responseDTO;
    }

    public List<TipoServicoResponseDTO> findAll() {
        List<TipoServico> servicos = tipoServicoRepository.findAll();
        return servicos.stream().map(tipoServicoMapper::toResponseDTO).collect(Collectors.toList());
    }

    public TipoServicoResponseDTO getById(Integer tipoServicoId){
        TipoServico servico = findById(tipoServicoId);
        return tipoServicoMapper.toResponseDTO(servico);
    }

    public void deleteById(Integer tipoServicoId){
        TipoServico servico = findById(tipoServicoId);
        tipoServicoRepository.delete(servico);
    }

    private void existTipoServicoByName(String nomeServico){
        if(tipoServicoRepository.existsTipoServicoByNomeServico(nomeServico)){
            throw new BusinessException("Já existe um serviço com este nome.");
        }
    }
    private TipoServico findById( Integer tipoServicoId) {
        return tipoServicoRepository.findById(tipoServicoId).orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrato pelo Id: " + tipoServicoId));
    }

    private void validarCamposObrigatoriosServico(TipoServicoDTO tipoServicoDTO) {
        List<String> camposObrigatorios = new ArrayList<>();

        if (Util.isNullOrEmpty(tipoServicoDTO.getNomeServico())){
            camposObrigatorios.add("Nome do Serviço");
        }
        if (Util.isNullOrEmpty(tipoServicoDTO.getPrecoServico().toString())) {
            camposObrigatorios.add("Preço do Serviço");
        }
        if (!camposObrigatorios.isEmpty()) {
            throw new ValidationException("Os seguintes campos são obrigatórios: " + String.join(", ", camposObrigatorios));
        }
    }
}
