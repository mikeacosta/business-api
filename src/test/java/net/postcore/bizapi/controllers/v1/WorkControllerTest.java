package net.postcore.bizapi.controllers.v1;

import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.api.v1.model.WorkListDTO;
import net.postcore.bizapi.services.ResourceNotFoundException;
import net.postcore.bizapi.services.WorkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WorkControllerTest extends AbstractRestControllerTest {

    @Mock
    WorkService workService;

    @InjectMocks
    WorkController workController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(workController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListWorks() throws Exception {
        WorkDTO work1 = new WorkDTO();
        work1.setName("Acme Services");
        work1.setDescription("Service provider");
        work1.setWorkUrl(WorkController.BASE_URL + "/1");

        WorkDTO work2 = new WorkDTO();
        work2.setName("Smith Janitorial");
        work2.setDescription("Custodian services");
        work2.setWorkUrl(WorkController.BASE_URL + "/2");

        when(workService.getAllWorks()).thenReturn(new WorkListDTO(Arrays.asList(work1, work2)));

        mockMvc.perform(get(WorkController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.works", hasSize(2)));
    }

    @Test
    public void testGetWorkById() throws Exception {
        // arrange
        WorkDTO work1 = new WorkDTO();
        work1.setName("Bob Builders");
        work1.setDescription("Construction services");
        work1.setWorkUrl(WorkController.BASE_URL + "/2");

        when(workService.getWorkById(anyLong())).thenReturn(work1);

        // act/assert
        mockMvc.perform(get(WorkController.BASE_URL + "/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Bob Builders")))
                .andExpect(jsonPath("$.description", equalTo("Construction services")));
    }

    @Test
    public void createNewWork() throws Exception {
        // arrange
        WorkDTO workDTO = new WorkDTO();
        workDTO.setName("Acme Services");
        workDTO.setDescription("Service provider");

        WorkDTO returnDTO = new WorkDTO();
        returnDTO.setName(workDTO.getName());
        returnDTO.setDescription(workDTO.getDescription());
        returnDTO.setWorkUrl(WorkController.BASE_URL + "/1");

        when(workService.createNewWork(any(WorkDTO.class))).thenReturn(returnDTO);

        // act/assert
        mockMvc.perform(post(WorkController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(workDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Acme Services")))
                .andExpect(jsonPath("$.description", equalTo("Service provider")))
                .andExpect(jsonPath("$.work_url", equalTo(WorkController.BASE_URL + "/1")));
    }

    @Test
    public void testUpdateWork() throws Exception {
        // arrange
        WorkDTO work = new WorkDTO();
        work.setName("Cat's Catering");
        work.setDescription("We make food");

        WorkDTO returnDTO = new WorkDTO();
        returnDTO.setName(work.getName());
        returnDTO.setDescription(work.getDescription());
        returnDTO.setWorkUrl(WorkController.BASE_URL + "/1");

        when(workService.saveWorkByDTO(anyLong(), any(WorkDTO.class))).thenReturn(returnDTO);

        // act/assert
        mockMvc.perform(put(WorkController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(work)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Cat's Catering")))
                .andExpect(jsonPath("$.description", equalTo("We make food")))
                .andExpect(jsonPath("$.work_url", equalTo(WorkController.BASE_URL + "/1")));
    }

    @Test
    public void testPatchWork() throws Exception {

        // arrange
        WorkDTO work = new WorkDTO();
        work.setName("Yard work");

        WorkDTO returnDTO = new WorkDTO();
        returnDTO.setName(work.getName());
        returnDTO.setDescription("Pulling weeds");
        returnDTO.setWorkUrl(WorkController.BASE_URL + "/1");

        when(workService.patchWork(anyLong(), any(WorkDTO.class))).thenReturn(returnDTO);

        // act/assert
        mockMvc.perform(patch(WorkController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(work)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Yard work")))
                .andExpect(jsonPath("$.description", equalTo("Pulling weeds")))
                .andExpect(jsonPath("$.work_url", equalTo(WorkController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteWork() throws Exception {
        mockMvc.perform(delete(WorkController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(workService).deleteWorkById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {
        when(workService.getWorkById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(WorkController.BASE_URL + "/7779311")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }    
}
