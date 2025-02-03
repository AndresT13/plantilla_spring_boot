package com.progamming.lite.thinking.tareapractica.service.impl;

import ch.qos.logback.core.net.server.Client;
import com.progamming.lite.thinking.tareapractica.exception.ClientNotFoundException;
import com.progamming.lite.thinking.tareapractica.model.ClientModel;
import com.progamming.lite.thinking.tareapractica.repository.ClientRepository;
import com.progamming.lite.thinking.tareapractica.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements IClientService {
    private final ClientRepository clientRepository;

    @Override
    public ClientModel salvar(ClientModel clientModel) {

        if (clientModel == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        if (clientRepository.existsByNumberDocument(clientModel.getNumberDocument())) {
            throw new IllegalArgumentException("Ya existe un cliente con este número de documento");
        } else {

            clientModel.setFirstName(clientModel.getFirstName());
            clientModel.setSecondName(clientModel.getSecondName());
            clientModel.setCity(clientModel.getCity());
            clientModel.setAddress(clientModel.getAddress());
            clientModel.setNumberDocument(clientModel.getNumberDocument());
            clientModel.setDocumentType(clientModel.getDocumentType());
            clientModel.setNumberPhone(clientModel.getNumberPhone());

            return clientRepository.save(clientModel);

        }

    }


    @Override
    public ClientModel actualizar(Long id, ClientModel clientModel) {
        // Buscar el cliente por ID
        Optional<ClientModel> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado para actualizar");
        }

        // Obtener cliente existente
        ClientModel existingClient = clientOptional.get();

        // Actualizar los campos con los nuevos valores
        existingClient.setFirstName(clientModel.getFirstName());
        existingClient.setSecondName(clientModel.getSecondName());
        existingClient.setCity(clientModel.getCity());
        existingClient.setAddress(clientModel.getAddress());
        existingClient.setNumberDocument(clientModel.getNumberDocument());
        existingClient.setDocumentType(clientModel.getDocumentType());
        existingClient.setNumberPhone(clientModel.getNumberPhone());

        // Guardar el cliente actualizado
        return clientRepository.save(existingClient);
    }

    @Override
    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<ClientModel> findById(Long id) {

        Optional<ClientModel> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new ClientNotFoundException("Cliente con ID " + id + " no encontrado");
        }
        return client;

    }


    @Override
    public void delete(Long id) {
        Optional<ClientModel> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    @Override
    public Optional<ClientModel> findByNumberDocument(Integer numberDocument) {

        Optional<ClientModel> client = clientRepository.findByNumberDocument(numberDocument);

        if (client.isEmpty()) {
            throw new ClientNotFoundException("Cliente con número de documento " + numberDocument + " no encontrado");
        }

        return client;
    }

}

