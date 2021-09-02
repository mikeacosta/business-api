package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.model.ClientDTO;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    ClientDTO createNewClient(ClientDTO clientDTO);

    ClientDTO saveClientByDTO(Long id, ClientDTO clientDTO);
}
