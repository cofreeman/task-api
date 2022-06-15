package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.entity.ProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectMembersRepository extends JpaRepository<ProjectMembers, ProjectMembers.Pk> {

    @Query("select pm from ProjectMembers pm where pm.projects.projectId = :projectId")
    List<ProjectMembers> findAllProjectMembersByProjectId(@Param("projectId") Long projectId);

    //해당 유저가 프로젝트에 참여하고 있는지 여부
    @Query("select pm from ProjectMembers pm where pm.pk.projectId = :projectId and pm.pk.userId = :userId")
    Optional<ProjectMembers> findByUserIdAndProjectsId(@Param("userId") Long userId,@Param("projectId") Long projectId);
}
