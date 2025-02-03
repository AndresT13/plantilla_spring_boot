package com.progamming.lite.thinking.tareapractica.controller;

import com.progamming.lite.thinking.tareapractica.dto.ClientRequest;
import com.progamming.lite.thinking.tareapractica.exception.ClientNotFoundException;

import com.progamming.lite.thinking.tareapractica.model.ClientModel;
import com.progamming.lite.thinking.tareapractica.service.IClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
@Slf4j
public class ClientController {

    private final IClientService iClientService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> salvar(@RequestBody @Valid @NotNull ClientRequest clientRequest,
                                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining())
            );
        }

        ClientModel clientModel = ClientModel.builder()
                .firstName(clientRequest.getFirstName())
                .secondName(clientRequest.getSecondName())
                .city(clientRequest.getCity())
                .address(clientRequest.getAddress())
                .numberDocument(clientRequest.getNumberDocument())
                .documentType(clientRequest.getDocumentType())
                .numberPhone(clientRequest.getNumberPhone())
                .build();

        iClientService.salvar(clientModel);
        log.info("Client {} guardado", clientModel.getId());

        // En vez de lanzar una excepción, devolvemos una respuesta con código 201
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente guardado con éxito");
    }

    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listar() {
        List<ClientModel> clientList = iClientService.findAll();

        if (clientList.isEmpty()) {
            throw new ClientNotFoundException("No se encontraron clientes");
        }
        return ResponseEntity.ok(clientList);
    }

    @GetMapping(path = "/listar/document/{numberDocument}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listar(@PathVariable Integer numberDocument) {
        // Buscar el cliente por el número de documento
        Optional<ClientModel> clientOptional = iClientService.findByNumberDocument(numberDocument);

        // Verificar si el cliente está presente
        if (clientOptional.isPresent()) {
            return ResponseEntity.ok(clientOptional.get());  // Retorna el cliente si se encuentra
        } else {
            // Si no se encuentra el cliente, lanzamos una excepción
            throw new ClientNotFoundException("Cliente con número de documento " + numberDocument + " no encontrado");
        }
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizar(@PathVariable Long id,
                                             @RequestBody @Valid ClientModel clientModel,
                                             BindingResult bindingResult) {

        // Validar errores de entrada
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining())
            );
        }

        try {
            // Delegamos la lógica de actualización al servicio
            ClientModel updatedClient = iClientService.actualizar(id, clientModel);

            // Retornamos el cliente actualizado con una respuesta exitosa (200 OK)
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException e) {
            // Si ocurre un error (cliente no encontrado, etc.), lanzamos un error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente con ID " + id + " no encontrado para actualizar");
        }
    }



    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        Optional<ClientModel> clientModelOptional = iClientService.findById(id);
        if (clientModelOptional.isEmpty()) {
            throw new ClientNotFoundException("Cliente no existe");
        }
        ClientModel clientModel = clientModelOptional.get();
        iClientService.delete(clientModelOptional.get().getId());
        // En vez de devolver "No Content", devolvemos un mensaje con 200 OK
        return ResponseEntity.ok("Cliente con ID " + id + " eliminado con éxito");
    }

}