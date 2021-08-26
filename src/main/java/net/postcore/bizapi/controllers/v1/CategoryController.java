package net.postcore.bizapi.controllers.v1;

import net.postcore.bizapi.api.v1.model.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/category";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getallCatetories(){
        List<CategoryDTO> categories = new ArrayList<>();
        CategoryDTO cat1 = new CategoryDTO();
        cat1.setId(1L);
        cat1.setName(("category 1"));
        categories.add(cat1);
        CategoryDTO cat2 = new CategoryDTO();
        cat2.setId(2L);
        cat2.setName(("category 2"));
        categories.add(cat2);
        return categories;
    }
}
