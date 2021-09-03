package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.ClientMapper;
import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.controllers.v1.ClientController;
import net.postcore.bizapi.domain.Client;
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
                    clientDTO.setClientUrl(ClientController.BASE_URL + "/" + client.getId());
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

    @Override
    public ClientDTO createNewClient(ClientDTO clientDTO) {
        return saveAndReturnDTO(clientMapper.clientDtoToClient(clientDTO));
    }

    private ClientDTO saveAndReturnDTO(Client client) {
        Client savedClient = clientRepository.save(client);
        ClientDTO returnDTO = clientMapper.clientToClientDTO(savedClient);
        returnDTO.setClientUrl(ClientController.BASE_URL + "/" + savedClient.getId());
        return returnDTO;
    }

    @Override
    public ClientDTO saveClientByDTO(Long id, ClientDTO clientDTO) {
        Client client = clientMapper.clientDtoToClient(clientDTO);
        client.setId(id);
        return saveAndReturnDTO(client);
    }

    @Override
    public ClientDTO patchClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id).map(client -> {

            if(clientDTO.getFirstname() != null)
                client.setFirstname(clientDTO.getFirstname());

            if(clientDTO.getLastname() != null)
                client.setLastname(clientDTO.getLastname());

            ClientDTO returnDTO = clientMapper.clientToClientDTO(clientRepository.save(client));
            returnDTO.setClientUrl(ClientController.BASE_URL + "/" + id);

            return returnDTO;
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
