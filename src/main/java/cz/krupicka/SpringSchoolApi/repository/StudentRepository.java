package cz.krupicka.SpringSchoolApi.repository;

import cz.krupicka.SpringSchoolApi.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
