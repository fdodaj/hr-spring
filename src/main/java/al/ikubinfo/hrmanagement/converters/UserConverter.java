package al.ikubinfo.hrmanagement.converters;

import al.ikubinfo.hrmanagement.dto.UserDto;
import al.ikubinfo.hrmanagement.entity.UserEntity;
import al.ikubinfo.hrmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserConverter implements BidirectionalConverter<UserDto, UserEntity> {

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private DepartmentConverter departmentConverter;

    @Override
    public UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setBirthday(entity.getBirthday());
        dto.setGender(entity.getGender());
        dto.setHireDate(entity.getHireDate());
        dto.setPaidTimeOff(entity.getPaidTimeOff());
        dto.setDeleted(entity.isDeleted());
        dto.setRole(roleConverter.toDto(entity.getRole()));
        dto.setDepartment(departmentConverter.toDto(entity.getDepartment()));
        return dto;
    }


    @Override
    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();

        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setBirthday(dto.getBirthday());
        entity.setGender(dto.getGender());
        entity.setHireDate(dto.getHireDate());
        entity.setPaidTimeOff(dto.getPaidTimeOff());
        entity.setDeleted(dto.isDeleted());
        entity.setRole(roleConverter.toEntity(dto.getRole()));
        entity.setDepartment(departmentConverter.toEntity(dto.getDepartment()));
        return entity;
    }
}
