package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Projects, Long> {

    @Query("select p from Projects p left join ProjectMembers pm on p.projectId = pm.projects.projectId where p.userId = :userId")
    List<Projects> findAllProjectByUserId(@Param("userId") Long userId);

}
