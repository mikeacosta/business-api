package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.WorkMapper;
import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.api.v1.model.WorkListDTO;
import net.postcore.bizapi.controllers.v1.WorkController;
import net.postcore.bizapi.domain.Work;
import net.postcore.bizapi.repositories.WorkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class WorkServiceTest {

    public static final Long ID = 1L;
    public static final String NAME = "Design and Build";
    public static final String DESC = "Design a house and build it";
    WorkService workService;
    
    @Mock
    WorkRepository workRepository;
    
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        workService = new WorkServiceImpl(WorkMapper.getInstance(), workRepository);
    }
    
    @Test
    public void getAllWorks() throws Exception {
        // arrange
        List<Work> works = Arrays.asList(new Work(), new Work(), new Work());
        when(workRepository.findAll()).thenReturn(works);

        // act
        WorkListDTO workDTOs = workService.getAllWorks();

        // assert
        assertEquals(3, workDTOs.getWorks().size());
    }
    
    @Test
    public void getWorkById() {
        Work work = new Work();
        work.setId(ID);
        work.setName(NAME);
        work.setDescription(DESC);

        when(workRepository.findById(anyLong())).thenReturn(java.util.Optional.of(work));

        WorkDTO workDTO = workService.getWorkById(ID);

        assertEquals(ID, workDTO.getId());
        assertEquals(NAME, workDTO.getName());
        assertEquals(DESC, workDTO.getDescription());
    }
    
    @Test
    public void createNewWork() {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setName("walk the dog");
        workDTO.setDescription("walk the dog daily at Noon");
        
        Work savedWork = new Work();
        savedWork.setName(workDTO.getName());
        savedWork.setDescription(workDTO.getDescription());
        savedWork.setId(1L);

        when(workRepository.save(any(Work.class))).thenReturn(savedWork);

        WorkDTO savedDto = workService.createNewWork(workDTO);

        assertEquals(workDTO.getName(), savedDto.getName());
        assertEquals(workDTO.getDescription(), savedDto.getDescription());
        assertEquals(WorkController.BASE_URL + "/1", savedDto.getWorkUrl());
    }

    @Test
    public void saveWorkByDTO() throws Exception {
        // arrange
        WorkDTO workDTO = new WorkDTO();
        workDTO.setName("Acme Services");
        workDTO.setDescription("Service provider");

        Work savedWork = new Work();
        savedWork.setName(workDTO.getName());
        savedWork.setDescription(workDTO.getDescription());
        savedWork.setId(1L);

        when(workRepository.save(any(Work.class))).thenReturn(savedWork);

        // act
        WorkDTO savedDto = workService.saveWorkByDTO(1L, workDTO);

        // assert
        assertEquals(workDTO.getName(), savedDto.getName());
        assertEquals(workDTO.getDescription(), savedDto.getDescription());
        assertEquals(WorkController.BASE_URL + "/1", savedDto.getWorkUrl());
    }

    @Test
    public void deleteWorkById() throws Exception {
        // arrange
        Long id = 1L;

        // act
        workService.deleteWorkById(id);

        // assert
        verify(workRepository, times(1)).deleteById(anyLong());
    }    
}
