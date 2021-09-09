package net.postcore.bizapi.controllers.v1;

import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.api.v1.model.WorkListDTO;
import net.postcore.bizapi.services.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WorkController.BASE_URL)
public class WorkController {

    public static final String BASE_URL = "/api/v1/works";
    
    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WorkListDTO getWorkList(){
        return workService.getAllWorks();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public WorkDTO getWorkById(@PathVariable Long id){
        return workService.getWorkById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkDTO createNewWork(@RequestBody WorkDTO workDTO){
        return workService.createNewWork(workDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public WorkDTO updateWork(@PathVariable Long id, @RequestBody WorkDTO workDTO){
        return workService.saveWorkByDTO(id, workDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public WorkDTO patchWork(@PathVariable Long id, @RequestBody WorkDTO workDTO){
        return workService.patchWork(id, workDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteWork(@PathVariable Long id){
        workService.deleteWorkById(id);
    }
}
