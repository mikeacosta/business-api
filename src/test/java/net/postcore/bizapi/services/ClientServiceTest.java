package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.ClientMapper;
import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.domain.Client;
import net.postcore.bizapi.repositories.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

;

public class ClientServiceTest {

    public static final Long ID = 1L;
    public static final String FIRSTNAME = "Joe";
    public static final String LASTNAME = "Blow";
    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        clientService = new ClientServiceImpl(ClientMapper.INSTANCE, clientRepository);
    }

    @Test
    public void getAllClients() throws Exception {
        List<Client> clients = Arrays.asList(new Client(), new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDTO> categoryDTOS = clientService.getAllClients();

        assertEquals(3, categoryDTOS.size());
    }

    @Test
    public void getClientById() throws Exception {
        Client client = new Client();
        client.setId(ID);
        client.setFirstname(FIRSTNAME);
        client.setLastname(LASTNAME);

        when(clientRepository.findById(anyLong())).thenReturn(java.util.Optional.of(client));

        ClientDTO clientDTO = clientService.getClientById(ID);

        assertEquals(ID, clientDTO.getId());
        assertEquals(FIRSTNAME, clientDTO.getFirstname());
        assertEquals(LASTNAME, clientDTO.getLastname());
    }
}
