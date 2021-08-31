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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTest {

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

        //given
        ClientDTO client1 = new ClientDTO();
        client1.setFirstname("Sade");
        client1.setLastname("Adu");
        client1.setClientUrl("/api/v1/clients/2");

        when(clientService.getClientById(anyLong())).thenReturn(client1);

        //when
        mockMvc.perform(get("/api/v1/clients/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Sade")));
    }    
}
