package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.ClientMapper;
import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    public final ClientMapper clientMapper;
    public final ClientRepository clientRepository;

    public ClientServiceImpl(ClientMapper clientMapper, ClientRepository clientRepository) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(client -> {
                    ClientDTO clientDTO = clientMapper.clientToClientDTO(client);
                    clientDTO.setClientUrl("/api/v1/customers/" + client.getId());
                    return clientDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::clientToClientDTO)
                .orElseThrow(RuntimeException::new);
    }
}
