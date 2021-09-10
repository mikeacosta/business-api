package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.CategoryDTO;
import net.postcore.bizapi.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private static CategoryMapper instance;

    private CategoryMapper() {

    }

    public static CategoryMapper getInstance() {
        if (instance == null)
            instance = new CategoryMapper();

        return instance;
    }

    public CategoryDTO categoryToCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName((category.getName()));
        return dto;
    }
}
