package com.lava_jato.services;

import com.lava_jato.entities.dto.responses.ClienteResumoDTO;
import com.lava_jato.entities.mapstructs.ClienteMapper;
import com.lava_jato.entities.model.Cliente;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResumoDTO getClienteById(Long clienteId) {
        Cliente cliente = findClienteById(clienteId);
        return clienteMapper.toResumoDTO(cliente);
    }

    public Cliente getClienteByIdEntity(Long clienteId) {
        return findClienteById(clienteId);
    }

//    public List<ClienteResumoDTO> findAllClientes() {
//        List<Cliente> clientes = clienteRepository.findAll();
//        return clientes.stream().map(clienteMapper::toResumoDTO).collect(Collectors.toList());
//    }

    public Page<ClienteResumoDTO> findAllClientes(Pageable pageable) {
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        return clientes.map(clienteMapper::toResumoDTO);
    }

    @Transactional
    public void deleteCliente(Long clienteId) {
        Cliente cliente = findClienteById(clienteId);
        clienteRepository.delete(cliente);
    }

    //TODO Criar uma busca por nome
    //TODO Criar uma busca por email

    private Cliente findClienteById(Long clienteId){
        return clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado pelo id: " + clienteId));
    }

}
