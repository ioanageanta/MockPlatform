package com.webstudent.platform.repository;


import com.webstudent.platform.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
    @Query("select e.date from Exam e where e.name=:subjectName")
    String findBySubject(@Param("subjectName") String subjectName);

    @Query("select e from Exam e where e.name=:subjectName")
    Exam findExamBySubject(@Param("subjectName") String subjectName);
}
