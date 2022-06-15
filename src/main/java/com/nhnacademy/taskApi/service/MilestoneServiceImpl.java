package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.MilestoneCreateDto;
import com.nhnacademy.taskApi.dto.MilestoneModifyDto;
import com.nhnacademy.taskApi.entity.Milestones;
import com.nhnacademy.taskApi.entity.Projects;
import com.nhnacademy.taskApi.exception.NotFoundMilestoneException;
import com.nhnacademy.taskApi.exception.NotFoundProjectException;
import com.nhnacademy.taskApi.exception.NotFoundProjectMemberException;
import com.nhnacademy.taskApi.repository.MilestoneRepository;
import com.nhnacademy.taskApi.repository.ProjectMembersRepository;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMembersRepository projectMembersRepository;

    @Override
    public Milestones createMilestone(Long userId, Long projectId, MilestoneCreateDto milestoneCreateDto) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        Projects projects = projectRepository.findById(projectId).orElseThrow(NotFoundProjectException::new);
        Milestones milestones = new Milestones(projects, milestoneCreateDto);
        return milestoneRepository.save(milestones);
    }

    @Override
    public List<Milestones> findAllMilestoneByProjectId(Long userId, Long projectId) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        return milestoneRepository.findAllMilestoneByProjectId(projectId);
    }

    @Override
    public Milestones getMilestoneById(Long userId, Long projectId,Long milestoneId) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        return milestoneRepository.findById(milestoneId).orElseThrow(NotFoundMilestoneException::new);
    }

    @Override
    public void deleteMilestoneById(Long userId, Long projectId,Long milestoneId) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        milestoneRepository.deleteById(milestoneId);
    }

    @Override
    public Milestones modifyMilestoneById(Long userId, Long projectId,Long milestoneId, MilestoneModifyDto milestoneModifyDto) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        Milestones milestones = milestoneRepository.findById(milestoneId).orElseThrow(NotFoundMilestoneException::new);
        milestones.setMilestoneName(milestoneModifyDto.getMilestoneName());
        milestones.setStartDate(milestoneModifyDto.getStartDate());
        milestones.setEndDate(milestoneModifyDto.getEndDate());
        return milestoneRepository.save(milestones);
    }
}
