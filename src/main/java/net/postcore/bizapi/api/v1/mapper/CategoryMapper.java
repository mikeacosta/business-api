package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.CategoryDTO;
import net.postcore.bizapi.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
