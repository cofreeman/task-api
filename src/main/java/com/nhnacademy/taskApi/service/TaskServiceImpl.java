package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.ModifyTaskDto;
import com.nhnacademy.taskApi.dto.TaskCreateDto;
import com.nhnacademy.taskApi.entity.Projects;
import com.nhnacademy.taskApi.entity.Tasks;
import com.nhnacademy.taskApi.exception.NotFoundProjectException;
import com.nhnacademy.taskApi.exception.NotFoundProjectMemberException;
import com.nhnacademy.taskApi.exception.NotFoundTasksException;
import com.nhnacademy.taskApi.repository.ProjectMembersRepository;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import com.nhnacademy.taskApi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMembersRepository projectMembersRepository;

    @Override
    public Tasks createTask(Long userId, Long projectId, TaskCreateDto taskCreateDto) {
        // todo:리팩토링
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        Projects project = projectRepository.findById(projectId).orElseThrow(NotFoundProjectException::new);
        Tasks tasks = new Tasks(project, null, taskCreateDto.getTaskTitle(), taskCreateDto.getTaskContent());
        return taskRepository.save(tasks);
    }

    @Override
    public List<Tasks> getAllTasks(Long userId,Long projectId) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        return taskRepository.findAllTaskByProjectId(projectId);
    }

    @Override
    public void deleteTask(Long userId, Long projectId, Long taskId) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        taskRepository.deleteById(taskId);
    }

    @Override
    public Tasks modifyTask(Long userId,Long projectId,Long taskId, ModifyTaskDto modifyTaskDto){
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        Tasks tasks = taskRepository.findById(taskId).orElseThrow(NotFoundTasksException::new);
        tasks.setTaskTitle(modifyTaskDto.getTaskTitle());
        tasks.setTaskContent(modifyTaskDto.getTaskContent());
        return taskRepository.save(tasks);
    }

    @Override
    public Tasks getTask(Long userId, Long projectId, Long taskId) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        return taskRepository.findById(taskId).orElseThrow(NotFoundTasksException::new);
    }
}
