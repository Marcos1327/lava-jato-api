package com.lava_jato.services;

import com.lava_jato.entities.model.Cliente;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente getPessoaFisicaById(Long clienteId) {
        return findClienteById(clienteId);
    }

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    public void deleteCliente(Long clienteId) {
        Cliente cliente = findClienteById(clienteId);
        clienteRepository.delete(cliente);
    }

    private Cliente findClienteById(Long clienteId){
        return clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado pelo id: " + clienteId));
    }

}
