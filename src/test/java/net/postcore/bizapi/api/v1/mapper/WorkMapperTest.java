package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.domain.Work;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkMapperTest {

    public static final String NAME = "yard work";
    public static final String DESC = "pull weeds";

    WorkMapper workMapper = WorkMapper.INSTANCE;

    @Test
    public void workToWorkDTO() throws Exception {
        // arrange
        Work work = new Work();
        work.setName(NAME);
        work.setDescription(DESC);

        // act
        WorkDTO workDTO = workMapper.workToWorkDTO(work);

        // assert
        assertEquals(work.getName(), workDTO.getName());
        assertEquals(work.getDescription(), workDTO.getDescription());
    }

    @Test
    public void workDTOtoWork() throws Exception {
        // arrange
        WorkDTO workDTO = new WorkDTO();
        workDTO.setName(NAME);
        workDTO.setDescription(DESC);

        // act
        Work work = workMapper.workDtoToWork(workDTO);

        // assert
        assertEquals(workDTO.getName(), work.getName());
        assertEquals(workDTO.getDescription(), work.getDescription());
    }    
}