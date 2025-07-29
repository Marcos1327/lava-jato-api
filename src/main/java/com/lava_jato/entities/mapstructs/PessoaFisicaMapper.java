package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.PessoaFisicaResponseDTO;
import com.lava_jato.entities.model.PessoaFisica;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PessoaFisicaMapper {

    PessoaFisicaResponseDTO toResponseDTO(PessoaFisica pessoaFisica);
    List<PessoaFisicaResponseDTO> toResponseDTOList(List<PessoaFisica> pessoaFisicas);
}
