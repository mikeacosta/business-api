package net.postcore.bizapi.controllers.v1;

import net.postcore.bizapi.api.v1.model.ProviderDTO;
import net.postcore.bizapi.api.v1.model.ProviderListDTO;
import net.postcore.bizapi.services.ProviderService;
import net.postcore.bizapi.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static net.postcore.bizapi.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProviderControllerTest {

    @Mock
    ProviderService providerService;

    @InjectMocks
    ProviderController providerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(providerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListProviders() throws Exception {
        ProviderDTO provider1 = new ProviderDTO();
        provider1.setName("Paul");
        provider1.setProviderUrl(ProviderController.BASE_URL + "/1");

        ProviderDTO provider2 = new ProviderDTO();
        provider2.setName("Sade");
        provider2.setProviderUrl(ProviderController.BASE_URL + "/2");

        when(providerService.getAllProviders()).thenReturn(new ProviderListDTO(Arrays.asList(provider1, provider2)));

        mockMvc.perform(get(ProviderController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.providers", hasSize(2)));
    }

    @Test
    public void testGetProviderById() throws Exception {
        // arrange
        ProviderDTO provider1 = new ProviderDTO();
        provider1.setName("Dude");
        provider1.setProviderUrl(ProviderController.BASE_URL + "/2");

        when(providerService.getProviderById(anyLong())).thenReturn(provider1);

        // act/assert
        mockMvc.perform(get(ProviderController.BASE_URL + "/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Dude")));
    }

    @Test
    public void createNewProvider() throws Exception {
        // arrange
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setName("Business Company");

        ProviderDTO returnDTO = new ProviderDTO();
        returnDTO.setName(providerDTO.getName());
        returnDTO.setProviderUrl(ProviderController.BASE_URL + "/1");

        when(providerService.createNewProvider(any(ProviderDTO.class))).thenReturn(returnDTO);

        // act/assert
        mockMvc.perform(post(ProviderController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(providerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Business Company")))
                .andExpect(jsonPath("$.provider_url", equalTo(ProviderController.BASE_URL + "/1")));
    }

    @Test
    public void testUpdateProvider() throws Exception {
        // arrange
        ProviderDTO provider = new ProviderDTO();
        provider.setName("Bob Builders");

        ProviderDTO returnDTO = new ProviderDTO();
        returnDTO.setName(provider.getName());
        returnDTO.setProviderUrl(ProviderController.BASE_URL + "/1");

        when(providerService.saveProviderByDTO(anyLong(), any(ProviderDTO.class))).thenReturn(returnDTO);

        // act/assert
        mockMvc.perform(put(ProviderController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(provider)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Bob Builders")))
                .andExpect(jsonPath("$.provider_url", equalTo(ProviderController.BASE_URL + "/1")));
    }

    @Test
    public void testPatchProvider() throws Exception {
        // arrange
        ProviderDTO provider = new ProviderDTO();
        provider.setName("Hank the Handyman");

        ProviderDTO returnDTO = new ProviderDTO();
        returnDTO.setName(provider.getName());
        returnDTO.setProviderUrl(ProviderController.BASE_URL + "/1");

        when(providerService.patchProvider(anyLong(), any(ProviderDTO.class))).thenReturn(returnDTO);

        // act/assert
        mockMvc.perform(patch(ProviderController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(provider)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Hank the Handyman")))
                .andExpect(jsonPath("$.provider_url", equalTo(ProviderController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteProvider() throws Exception {
        mockMvc.perform(delete(ProviderController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(providerService).deleteProviderById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {
        when(providerService.getProviderById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(ProviderController.BASE_URL + "/8675309")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }    
}
