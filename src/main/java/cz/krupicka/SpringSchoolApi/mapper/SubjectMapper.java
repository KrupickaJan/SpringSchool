package cz.krupicka.SpringSchoolApi.mapper;

import cz.krupicka.SpringSchoolApi.dto.SubjectDto;
import cz.krupicka.SpringSchoolApi.entity.SubjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectEntity toEntity(SubjectDto subjectDto);
    SubjectDto toDto(SubjectEntity subjectEntity);
}