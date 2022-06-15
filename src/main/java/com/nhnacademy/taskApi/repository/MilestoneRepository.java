package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.entity.Milestones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestones,Long> {

    @Query("select m from Milestones m where m.projectId.projectId = :projectId")
    List<Milestones> findAllMilestoneByProjectId(@Param("projectId") Long projectId);
}
