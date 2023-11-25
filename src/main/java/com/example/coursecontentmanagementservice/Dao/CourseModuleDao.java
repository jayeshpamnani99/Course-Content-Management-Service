package com.example.coursecontentmanagementservice.Dao;

import com.example.coursecontentmanagementservice.Model.CourseModule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseModuleDao extends CrudRepository<CourseModule, Integer> {

    @Query(value = "SELECT cm FROM CourseModule cm WHERE cm.courseId = :courseId ORDER BY cm.serialNumber ASC")
    public List<CourseModule> findByCourseId(@Param("courseId") Integer courseId);
}
