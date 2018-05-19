package com.webstudent.platform.repository;

import com.webstudent.platform.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("select s.grade from Subject s where s.user.email=:email")
    Integer findGrade(@Param("email") String email);

    @Query("select count (s.grade) from Subject s where s.grade<5")
    Integer countRetests();

    @Query("select count (s.grade) from Subject s where s.grade is null")
    Integer countAbsences();
}
