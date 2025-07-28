package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.ClienteResumoDTO;
import com.lava_jato.entities.dto.VeiculoDTO;
import com.lava_jato.entities.dto.responses.VeiculoResponseDTO;
import com.lava_jato.entities.model.Cliente;
import com.lava_jato.entities.model.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClienteMapper.class})
public interface VeiculoMapper {
    Veiculo toEntity(VeiculoDTO veiculoDTO);

    @Mapping(target = "proprietario", source = "proprietario")
    VeiculoResponseDTO toResponseDTO(Veiculo veiculo);

}
