package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.MilestoneCreateDto;
import com.nhnacademy.taskApi.dto.MilestoneModifyDto;
import com.nhnacademy.taskApi.entity.Milestones;

import java.util.List;

public interface MilestoneService {

    Milestones createMilestone(Long userId, Long projectId, MilestoneCreateDto milestoneCreateDto);

    List<Milestones> findAllMilestoneByProjectId(Long userId, Long projectId);

    void deleteMilestoneById(Long userId, Long projectId, Long milestoneId);

    Milestones modifyMilestoneById(Long userId, Long projectId, Long milestoneId, MilestoneModifyDto milestoneModifyDto);

    Milestones getMilestoneById(Long userId, Long projectId, Long milestoneId);
}
