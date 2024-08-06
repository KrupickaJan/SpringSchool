package cz.krupicka.SpringSchoolApi.mapper;

import cz.krupicka.SpringSchoolApi.dto.StudentDto;
import cz.krupicka.SpringSchoolApi.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentEntity toEntity(StudentDto studentDto);
    StudentDto toDto(StudentEntity studentEntity);
}
