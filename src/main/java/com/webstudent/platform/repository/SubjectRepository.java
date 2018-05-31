package com.webstudent.platform.repository;

import com.webstudent.platform.model.Exam;
import com.webstudent.platform.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("select s.grade from Subject s where s.user.email=:email and s.exam.name=:name")
    Integer findGrade(@Param("email") String email, @Param("name") String examName);

    @Query("select count (s.grade) from Subject s where s.grade<5")
    Integer countRetests();

    @Query("select count (s.grade) from Subject s where s.grade is null")
    Integer countAbsences();

    @Query("select s.exam from Subject s where s.user.email=:email")
    List<Exam> getExams(@Param("email") String email);

    @Query("select s.exam from Subject s where s.id=:id")
    Exam getExam(@Param("id") Integer id);

    @Query("select s from Subject s where s.user.id=:user_id and s.exam.id=:exam_id")
    Subject getSubjectByUserAndExam(@Param("user_id") Integer userId, @Param("exam_id") Integer examId);
}
