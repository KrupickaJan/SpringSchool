package cz.krupicka.SpringSchoolApi.repository;

import cz.krupicka.SpringSchoolApi.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<GradeEntity, Integer> {
}
