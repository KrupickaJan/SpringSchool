package cz.krupicka.SpringSchoolApi.service;

import cz.krupicka.SpringSchoolApi.dto.SubjectDto;
import cz.krupicka.SpringSchoolApi.entity.SubjectEntity;
import cz.krupicka.SpringSchoolApi.mapper.SubjectMapper;
import cz.krupicka.SpringSchoolApi.repository.SubjectRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectMapper subjectMapper;

    public List<SubjectDto> getSubjects(){
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        List<SubjectDto> subjectDtos = new ArrayList<>();


        for(SubjectEntity subjectEntity : subjectEntities){
            subjectDtos.add(subjectMapper.toDto(subjectEntity));
        }
        return subjectDtos;
    }

    public SubjectDto addSubject(SubjectDto subjectDto){
        SubjectEntity subjectEntity = subjectMapper.toEntity(subjectDto);
        SubjectEntity savedEntity =  subjectRepository.save(subjectEntity);
        return subjectMapper.toDto(savedEntity);
    }

    public SubjectDto getSubject(Integer id){

        if (!subjectRepository.existsById(id)){
            throw new EntityExistsException();
        }
        SubjectEntity subjectEntity = subjectRepository.getReferenceById(id);
        return subjectMapper.toDto(subjectEntity);
    }

    public SubjectDto editSubject(Integer id, SubjectDto subjectDto){
        SubjectEntity subjectToEdit = subjectRepository
                .findById(id)
                .orElseThrow(EntityExistsException::new);

        SubjectEntity subjectEntity = subjectMapper.toEntity(subjectDto);
        subjectEntity.setId(id);
        SubjectEntity savedSubject = subjectRepository.save(subjectEntity);
        return subjectMapper.toDto(savedSubject);
    }

    public SubjectDto deleteSubject(Integer id){
        SubjectEntity subjectToDelete = subjectRepository
                .findById(id)
                .orElseThrow(EntityExistsException::new);
        SubjectDto deletedSubject = subjectMapper.toDto(subjectToDelete);
        subjectRepository.delete(subjectToDelete);
        return deletedSubject;
    }
}
