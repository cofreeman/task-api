package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    @Query("select t from Tasks t where t.projects.projectId = :projectId")
    List<Tasks> findAllTaskByProjectId(@Param("projectId") Long projectId);
}
