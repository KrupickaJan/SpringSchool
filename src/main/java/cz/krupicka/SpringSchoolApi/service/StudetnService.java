package cz.krupicka.SpringSchoolApi.service;

import cz.krupicka.SpringSchoolApi.dto.StudentDto;
import cz.krupicka.SpringSchoolApi.entity.StudentEntity;
import cz.krupicka.SpringSchoolApi.mapper.StudentMapper;
import cz.krupicka.SpringSchoolApi.repository.StudentRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudetnService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    public List<StudentDto> getStudents(){
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();


        for(StudentEntity studentEntity : studentEntities){
            studentDtos.add(studentMapper.toDto(studentEntity));
        }
        return studentDtos;
    }

    public StudentDto addStudent(StudentDto studentDto){
        StudentEntity studentEntity = studentMapper.toEntity(studentDto);
        StudentEntity savedEntity =  studentRepository.save(studentEntity);
        return studentMapper.toDto(savedEntity);
    }

    public StudentDto getStudent(Integer id){
//        studentRepository.findById(id).orElseThrow(EntityExistsException::new);
        if (!studentRepository.existsById(id)){
            throw new EntityExistsException();
        }
        StudentEntity studentEntity = studentRepository.getReferenceById(id);
        return studentMapper.toDto(studentEntity);
    }

    public StudentDto editStudent(Integer id, StudentDto studentDto){
        StudentEntity studentToEdit = studentRepository
                .findById(id)
                .orElseThrow(EntityExistsException::new);

        StudentEntity studentEntity = studentMapper.toEntity(studentDto);
        studentEntity.setId(id);
        StudentEntity savedStudent = studentRepository.save(studentEntity);
        return studentMapper.toDto(savedStudent);
    }

    public StudentDto deleteStudent(Integer id){
        StudentEntity studentToDelete = studentRepository
                .findById(id)
                .orElseThrow(EntityExistsException::new);
        StudentDto deletedStudent = studentMapper.toDto(studentToDelete);
        studentRepository.delete(studentToDelete);
        return deletedStudent;
    }
}
