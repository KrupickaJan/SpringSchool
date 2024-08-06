package cz.krupicka.SpringSchoolApi.service;

import cz.krupicka.SpringSchoolApi.dto.GradeDto;
import cz.krupicka.SpringSchoolApi.dto.SubjectDto;
import cz.krupicka.SpringSchoolApi.entity.GradeEntity;
import cz.krupicka.SpringSchoolApi.entity.SubjectEntity;
import cz.krupicka.SpringSchoolApi.mapper.GradeMapper;
import cz.krupicka.SpringSchoolApi.repository.GradeRepository;
import cz.krupicka.SpringSchoolApi.repository.StudentRepository;
import cz.krupicka.SpringSchoolApi.repository.SubjectRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    GradeMapper gradeMapper;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public GradeDto addGrade (GradeDto gradeDto){
        GradeEntity gradeToAdd = gradeMapper.toEntity(gradeDto);
        gradeToAdd.setStudent(studentRepository.getReferenceById(gradeDto.getStudentId()));
        gradeToAdd.setSubject(subjectRepository.getReferenceById(gradeDto.getSubjectId()));
        return gradeMapper.toDto(gradeRepository.save(gradeToAdd));
    }

    public List<GradeDto> getAllGrades(){
        List<GradeDto> allGrades = new ArrayList<>();
        for(GradeEntity gradeEntity : gradeRepository.findAll()){
            allGrades.add(gradeMapper.toDto(gradeEntity));
        }
        return allGrades;
    }

    public GradeDto getGrade(int gradeId){
        return gradeMapper.toDto(gradeRepository.getReferenceById(gradeId));
    }

    public GradeDto editGrade(int gradeId, GradeDto gradeDto) {
        gradeDto.setId(gradeId);
        GradeEntity gradeEntity = gradeRepository.getReferenceById(gradeId);
        gradeMapper.updateEntity(gradeDto, gradeEntity);
        gradeEntity.setSubject(subjectRepository.getReferenceById(gradeDto.getSubjectId()));
        gradeEntity.setStudent(studentRepository.getReferenceById(gradeDto.getStudentId()));
        return gradeMapper.toDto(gradeRepository.save(gradeEntity));
    }

    public GradeDto deleteGrade(int gradeId){
        GradeEntity gradeToDelete = gradeRepository.getReferenceById(gradeId);
        GradeDto deletedGrade = gradeMapper.toDto(gradeToDelete);
        gradeRepository.delete(gradeToDelete);
        return deletedGrade;
    }
}
