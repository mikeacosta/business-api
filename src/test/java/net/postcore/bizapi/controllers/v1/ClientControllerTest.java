package net.postcore.bizapi.controllers.v1;

import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.services.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTest extends AbstractRestControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    ClientController clientController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testListClients() throws Exception {
        ClientDTO client1 = new ClientDTO();
        client1.setFirstname("Paul");
        client1.setLastname("Weller");
        client1.setClientUrl("/api/v1/clients/1");

        ClientDTO client2 = new ClientDTO();
        client2.setFirstname("Sade");
        client2.setLastname("Adu");
        client2.setClientUrl("/api/v1/clients/2");

        when(clientService.getAllClients()).thenReturn(Arrays.asList(client1, client2));

        mockMvc.perform(get("/api/v1/clients/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clients", hasSize(2)));
    }

    @Test
    public void testGetClientById() throws Exception {

        // arrange
        ClientDTO client1 = new ClientDTO();
        client1.setFirstname("Sade");
        client1.setLastname("Adu");
        client1.setClientUrl("/api/v1/clients/2");

        when(clientService.getClientById(anyLong())).thenReturn(client1);

        // act/assert
        mockMvc.perform(get("/api/v1/clients/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Sade")));
    }

    @Test
    public void createNewClient() throws Exception {
        // arrange
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstname("Charlie");
        clientDTO.setLastname("Brown");

        ClientDTO returnDTO = new ClientDTO();
        returnDTO.setFirstname(clientDTO.getFirstname());
        returnDTO.setLastname(clientDTO.getLastname());
        returnDTO.setClientUrl("/api/v1/clients/1");

        when(clientService.createNewClient(any(ClientDTO.class))).thenReturn(returnDTO);

        // act/assert
        mockMvc.perform(post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Charlie")))
                .andExpect(jsonPath("$.lastname", equalTo("Brown")))
                .andExpect(jsonPath("$.client_url", equalTo("/api/v1/clients/1")));
    }

    @Test
    public void testUpdateClient() throws Exception {
        // arrange
        ClientDTO client = new ClientDTO();
        client.setFirstname("Beyonce");
        client.setLastname("Knowles");

        ClientDTO returnDTO = new ClientDTO();
        returnDTO.setFirstname(client.getFirstname());
        returnDTO.setLastname(client.getLastname());
        returnDTO.setClientUrl("/api/v1/clients/1");

        when(clientService.saveClientByDTO(anyLong(), any(ClientDTO.class))).thenReturn(returnDTO);

        // act/assert
        mockMvc.perform(put("/api/v1/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Beyonce")))
                .andExpect(jsonPath("$.lastname", equalTo("Knowles")))
                .andExpect(jsonPath("$.client_url", equalTo("/api/v1/clients/1")));
    }

    @Test
    public void testPatchClient() throws Exception {

        // arrange
        ClientDTO client = new ClientDTO();
        client.setFirstname("Freddie");

        ClientDTO returnDTO = new ClientDTO();
        returnDTO.setFirstname(client.getFirstname());
        returnDTO.setLastname("Mercury");
        returnDTO.setClientUrl("/api/v1/clients/1");

        when(clientService.patchClient(anyLong(), any(ClientDTO.class))).thenReturn(returnDTO);

        // act/assert
        mockMvc.perform(patch("/api/v1/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Freddie")))
                .andExpect(jsonPath("$.lastname", equalTo("Mercury")))
                .andExpect(jsonPath("$.client_url", equalTo("/api/v1/clients/1")));
    }    
}
