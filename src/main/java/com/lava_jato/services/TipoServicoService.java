package com.lava_jato.services;

import com.lava_jato.entities.dto.request.TipoServicoDTO;
import com.lava_jato.entities.dto.responses.TipoServicoResponseDTO;
import com.lava_jato.entities.mapstructs.TipoServicoMapper;
import com.lava_jato.entities.model.TipoServico;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.repositories.TipoServicoRepository;
import com.lava_jato.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class TipoServicoService {

    private final TipoServicoRepository tipoServicoRepository;
    private final TipoServicoMapper tipoServicoMapper;

    public TipoServicoService(TipoServicoRepository tipoServicoRepository,  TipoServicoMapper tipoServicoMapper) {
        this.tipoServicoRepository = tipoServicoRepository;
        this.tipoServicoMapper = tipoServicoMapper;
    }

    @Transactional
    public TipoServicoResponseDTO create(TipoServicoDTO tipoServicoDTO) {
        validarCamposObrigatorios(tipoServicoDTO);
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

    @Transactional
    public TipoServicoResponseDTO update (Long tipoServicoId, TipoServicoDTO tipoServicoDTO) {
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

    public Page<TipoServicoResponseDTO> findAll(Pageable pageable) {
        Page<TipoServico> servicos = tipoServicoRepository.findAll(pageable);
        return  servicos.map(tipoServicoMapper::toResponseDTO);
    }

    public TipoServicoResponseDTO getById(Long tipoServicoId){
        TipoServico servico = findById(tipoServicoId);
        return tipoServicoMapper.toResponseDTO(servico);
    }

    public TipoServico getTipoServicoByIdEntity(Long tipoServicoId){
        return findById(tipoServicoId);
    }

    @Transactional
    public void deleteById(Long tipoServicoId){
        TipoServico servico = findById(tipoServicoId);
        tipoServicoRepository.delete(servico);
    }

    private void existTipoServicoByName(String nomeServico){
        if(tipoServicoRepository.existsTipoServicoByNomeServico(nomeServico)){
            throw new BusinessException("Já existe um serviço com este nome.");
        }
    }
    private TipoServico findById(Long tipoServicoId) {
        return tipoServicoRepository.findById(tipoServicoId).orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrato pelo Id: " + tipoServicoId));
    }
    private void validarCamposObrigatorios(TipoServicoDTO tipoServicoDTO) {
        Map<String, Object> camposObrigatorios = new HashMap<>();
        camposObrigatorios.put("Nome do Serviço", tipoServicoDTO.getNomeServico());
        camposObrigatorios.put("Preço do Serviço", tipoServicoDTO.getPrecoServico());

        Util.validarCamposObrigatorios(camposObrigatorios);

    }
}
