package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.ClienteResumoDTO;
import com.lava_jato.entities.model.Cliente;
import com.lava_jato.entities.model.PessoaFisica;
import com.lava_jato.entities.model.PessoaJuridica;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {
    
    default ClienteResumoDTO toResumoDTO(Cliente cliente) {
        if (cliente == null) return null;
        ClienteResumoDTO dto = new ClienteResumoDTO();
        dto.setClienteId(cliente.getClienteId());
        dto.setEmail(cliente.getEmail());

        if (cliente instanceof PessoaFisica pf) {
            dto.setNome(pf.getNome());
        } else if (cliente instanceof PessoaJuridica pj) {
            dto.setNome(pj.getNomeResponsavel());
        }

        return dto;
    }
}
