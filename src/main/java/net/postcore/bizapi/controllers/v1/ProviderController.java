package net.postcore.bizapi.controllers.v1;

import net.postcore.bizapi.api.v1.model.ProviderDTO;
import net.postcore.bizapi.api.v1.model.ProviderListDTO;
import net.postcore.bizapi.services.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProviderController.BASE_URL)
public class ProviderController {

    public static final String BASE_URL = "/api/v1/providers";

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProviderListDTO getProviderList(){
        return providerService.getAllProviders();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProviderDTO getProviderById(@PathVariable Long id){
        return providerService.getProviderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderDTO createNewProvider(@RequestBody ProviderDTO providerDTO){
        return providerService.createNewProvider(providerDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProviderDTO updateProvider(@PathVariable Long id, @RequestBody ProviderDTO providerDTO){
        return providerService.saveProviderByDTO(id, providerDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProviderDTO patchProvider(@PathVariable Long id, @RequestBody ProviderDTO providerDTO){
        return providerService.patchProvider(id, providerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteProvider(@PathVariable Long id){
        providerService.deleteProviderById(id);
    }    
}
