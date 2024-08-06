package cz.krupicka.SpringSchoolApi.repository;

import cz.krupicka.SpringSchoolApi.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
    Optional<SubjectEntity> findByName(Spring name);
}
