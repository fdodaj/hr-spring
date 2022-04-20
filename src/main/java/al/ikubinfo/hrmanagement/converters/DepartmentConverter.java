package al.ikubinfo.hrmanagement.converters;

import al.ikubinfo.hrmanagement.dto.DepartmentDto;
import al.ikubinfo.hrmanagement.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter implements BidirectionalConverter<DepartmentDto, DepartmentEntity>{


    @Override
    public DepartmentDto toDto(DepartmentEntity entity) {

        DepartmentDto dto = new DepartmentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDeleted(entity.getDeleted());

        return dto;
    }

    @Override
    public DepartmentEntity toEntity(DepartmentDto dto) {

        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDeleted(dto.getDeleted());

        return entity;
    }
}
