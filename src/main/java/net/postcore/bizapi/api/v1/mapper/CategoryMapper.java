package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.CategoryDTO;
import net.postcore.bizapi.domain.Category;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

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

    public Set<CategoryDTO> categoriesToCategoryDTOs(Set<Category> categories) {
        Set<CategoryDTO> categoryDTOs = new HashSet<>();
        categories.forEach(c -> {
            categoryDTOs.add(categoryToCategoryDTO(c));
        });
        return categoryDTOs;
    }

    public Category categoryDtoToCategory(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    public Set<Category> categoryDtosToCategories(Set<CategoryDTO> categoryDTOs) {
        Set<Category> categories = new HashSet<>();
        categoryDTOs.forEach(dto -> {
            categories.add(categoryDtoToCategory(dto));
        });
        return categories;
    }
}
