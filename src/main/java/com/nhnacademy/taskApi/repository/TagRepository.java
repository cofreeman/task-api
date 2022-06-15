package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface TagRepository extends JpaRepository<Tags, Long> {

    @Query("select t from Tags t where t.projects.projectId = :projectId")
    List<Tags> findAllTagsByProjectId(@Param("projectId") Long projectId);
}
