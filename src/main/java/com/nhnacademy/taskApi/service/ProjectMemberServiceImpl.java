package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.ProjectMemberCreateDto;
import com.nhnacademy.taskApi.entity.ProjectMembers;
import com.nhnacademy.taskApi.entity.Projects;
import com.nhnacademy.taskApi.exception.NotFoundProjectException;
import com.nhnacademy.taskApi.exception.NotProjectAdminException;
import com.nhnacademy.taskApi.repository.ProjectMembersRepository;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMembersRepository projectMembersRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ProjectMembers createProjectMember(Long userId, Long projectId, ProjectMemberCreateDto projectMemberCreateDto) {
        //todo: 리팩토링
        if (!projectRepository.findById(projectId)
                .orElseThrow(NotFoundProjectException::new)
                .getUserId()
                .equals(userId)) {
            throw new NotProjectAdminException();
        }
        Projects projects = projectRepository.findById(projectId).orElseThrow(NotFoundProjectException::new);
        ProjectMembers projectMembers = new ProjectMembers(projects, projectMemberCreateDto);
        return projectMembersRepository.save(projectMembers);
    }

    @Override
    public List<ProjectMembers> findAllProjectMembers(Long userId, Long projectId) {
        if (!projectRepository.findById(projectId)
                .orElseThrow(NotFoundProjectException::new)
                .getUserId()
                .equals(userId)) {
            throw new NotProjectAdminException();
        }
        return projectMembersRepository.findAllProjectMembersByProjectId(projectId);
    }

}
