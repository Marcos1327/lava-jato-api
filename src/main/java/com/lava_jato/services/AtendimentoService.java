package com.lava_jato.services;

import com.lava_jato.entities.dto.request.AtendimentoDTO;
import com.lava_jato.entities.dto.responses.AtendimentoResponseDTO;
import com.lava_jato.entities.enums.StatusAtendimento;
import com.lava_jato.entities.mapstructs.AtendimentoMapper;
import com.lava_jato.entities.model.Atendimento;
import com.lava_jato.entities.model.Cliente;
import com.lava_jato.entities.model.Veiculo;
import com.lava_jato.repositories.AtendimentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final ClienteService clienteService;
    private final VeiculoService veiculoService;
    private final AtendimentoMapper atendimentoMapper;

    public AtendimentoService(AtendimentoRepository atendimentoRepository, ClienteService clienteService, VeiculoService veiculoService, AtendimentoMapper atendimentoMapper) {
        this.atendimentoRepository = atendimentoRepository;
        this.clienteService = clienteService;
        this.veiculoService = veiculoService;
        this.atendimentoMapper = atendimentoMapper;
    }

    public AtendimentoResponseDTO create(AtendimentoDTO atendimentoDTO) {
        Cliente cliente = clienteService.getClienteByIdEntity(atendimentoDTO.getClienteId());
        Veiculo veiculo = veiculoService.findByIdEntity(atendimentoDTO.getVeiculoId());

        Atendimento atendimento = new Atendimento();
        atendimento.setCliente(cliente);
        atendimento.setVeiculo(veiculo);
        atendimento.setStatusAtendimento(StatusAtendimento.NA_FILA);
        atendimento.setDataCriacao(LocalDate.now());

        atendimentoRepository.save(atendimento);
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoMapper.toResponseDTO(atendimento);

        return  atendimentoResponseDTO;
    }
}
