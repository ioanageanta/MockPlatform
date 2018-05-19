package com.webstudent.platform.repository;


import com.webstudent.platform.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
//    @Query("select d.user from Device d where d.androidId=:androidId")
//    User findUserIdByAndroidId(@Param("androidId") String androidId);
//
//    @Query("select d.id from Device d where d.androidId=:androidId")
//    Integer findDevice(@Param("androidId") String androidId);
}
