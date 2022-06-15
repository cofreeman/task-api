package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.ModifyTaskDto;
import com.nhnacademy.taskApi.dto.TaskCreateDto;
import com.nhnacademy.taskApi.entity.Tasks;

import java.util.List;

public interface TaskService {
    Tasks createTask(Long userId,Long projectId, TaskCreateDto taskCreateDto);

    List<Tasks> getAllTasks(Long userId,Long projectId);

    void deleteTask(Long userId, Long projectId, Long taskId);

    Tasks modifyTask(Long userId,Long projectId,Long taskId, ModifyTaskDto modifyTaskDto);

    Tasks getTask(Long userId, Long projectId, Long taskId);
}
