package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.domain.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientMapperTest {

    public static final Long ID = 1L;
    public static final String FIRSTNAME = "Joe";
    public static final String LASTNAME = "Blow";

    ClientMapper clientMapper = ClientMapper.getInstance();

    @Test
    public void clientToClientDTO() throws Exception {
        Client client = new Client();
        client.setId(ID);
        client.setFirstname(FIRSTNAME);
        client.setLastname(LASTNAME);

        ClientDTO clientDTO = clientMapper.clientToClientDTO(client);

        assertEquals(ID, clientDTO.getId());
        assertEquals(FIRSTNAME, clientDTO.getFirstname());
        assertEquals(LASTNAME, clientDTO.getLastname());
    }

    @Test
    public void clientDtoToClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(ID);
        clientDTO.setFirstname(FIRSTNAME);
        clientDTO.setLastname(LASTNAME);

        Client client = clientMapper.clientDtoToClient(clientDTO);

        assertEquals(ID, client.getId());
        assertEquals(FIRSTNAME, client.getFirstname());
        assertEquals(LASTNAME, client.getLastname());
    }
}
