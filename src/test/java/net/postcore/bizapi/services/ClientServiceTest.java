package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.ClientMapper;
import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.controllers.v1.ClientController;
import net.postcore.bizapi.domain.Client;
import net.postcore.bizapi.repositories.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


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
        clientService = new ClientServiceImpl(ClientMapper.getInstance(), clientRepository);
    }

    @Test
    public void getAllClients() throws Exception {
        List<Client> clients = Arrays.asList(new Client(), new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDTO> clientDTOs = clientService.getAllClients();

        assertEquals(3, clientDTOs.size());
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

    @Test
    public void createNewClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstname("Joe");
        clientDTO.setLastname("Blow");

        Client savedClient = new Client();
        savedClient.setFirstname(clientDTO.getFirstname());
        savedClient.setLastname(clientDTO.getLastname());
        savedClient.setId(1L);

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        ClientDTO savedDto = clientService.createNewClient(clientDTO);

        assertEquals(clientDTO.getFirstname(), savedDto.getFirstname());
        assertEquals(clientDTO.getLastname(), savedDto.getLastname());
    }

    @Test
    public void saveClientByDTO() throws Exception {
        // arrange
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstname("Madonna");
        clientDTO.setLastname("Ciccone");

        Client savedClient = new Client();
        savedClient.setFirstname(clientDTO.getFirstname());
        savedClient.setLastname(clientDTO.getLastname());
        savedClient.setId(1L);

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        // act
        ClientDTO savedDto = clientService.saveClientByDTO(1L, clientDTO);

        // assert
        assertEquals(clientDTO.getFirstname(), savedDto.getFirstname());
        assertEquals(clientDTO.getLastname(), savedDto.getLastname());
    }

    @Test
    public void deleteClientById() throws Exception {
        // arrange
        Long id = 1L;

        // act
        clientService.deleteClientById(id);

        // assert
        verify(clientRepository, times(1)).deleteById(anyLong());
    }
}
