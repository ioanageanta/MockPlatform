package com.webstudent.platform.repository;

import com.webstudent.platform.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("select s.grade from Subject s where s.user.email=:email and s.exam.name=:name")
    Integer findGrade(@Param("email") String email, @Param("name") String examName);

    @Query("select count (s.grade) from Subject s where s.grade<5")
    Integer countRetests();

    @Query("select count (s.grade) from Subject s where s.grade is null")
    Integer countAbsences();
}
