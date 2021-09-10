package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.domain.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    private static ClientMapper instance;

    private ClientMapper() {

    }

    public static ClientMapper getInstance() {
        if (instance == null)
            instance = new ClientMapper();

        return instance;
    }

    public ClientDTO clientToClientDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setFirstname(client.getFirstname());
        dto.setLastname(client.getLastname());
        return dto;
    }

    public Client clientDtoToClient(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setFirstname(dto.getFirstname());
        client.setLastname(dto.getLastname());
        return client;
    }
}
