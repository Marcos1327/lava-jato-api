package com.lava_jato.services;

import com.lava_jato.entities.dto.ClienteResumoDTO;
import com.lava_jato.entities.mapstructs.ClienteMapper;
import com.lava_jato.entities.model.Cliente;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ClienteResumoDTO> findAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(clienteMapper::toResumoDTO).collect(Collectors.toList());
    }

    public void deleteCliente(Long clienteId) {
        Cliente cliente = findClienteById(clienteId);
        clienteRepository.delete(cliente);
    }

    //TODO Criar uma busca por nome
    //TODO Criar uma busca por email
    //TODO Criar DTO para resposta e não enviar a entidade diretamente

    private Cliente findClienteById(Long clienteId){
        return clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado pelo id: " + clienteId));
    }

}
