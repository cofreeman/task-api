package com.nhnacademy.taskApi.controller;


import com.nhnacademy.taskApi.dto.ModifyTaskDto;
import com.nhnacademy.taskApi.dto.TaskCreateDto;
import com.nhnacademy.taskApi.entity.Tasks;
import com.nhnacademy.taskApi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    //태스크 추가
    @PostMapping("users/{userId}/projects/{projectId}/tasks")
    public ResponseEntity<Tasks> createTask(@PathVariable("userId") Long userId,
                                            @PathVariable("projectId") Long projectId,
                                            @RequestBody TaskCreateDto taskCreateDto) {
        Tasks tasks = taskService.createTask(userId, projectId, taskCreateDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tasks);
    }

    //프로젝트의 모든 태스크 반환
    @GetMapping("users/{userId}/projects/{projectId}/tasks")
    public ResponseEntity<List<Tasks>> getAllTaskByProjectId(@PathVariable("userId") Long userId,
                                                             @PathVariable("projectId") Long projectId) {

        List<Tasks> tasks = taskService.getAllTasks(userId,projectId);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tasks);
    }
    //태스크 단건 조회
    @GetMapping("users/{userId}/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<Tasks> getAllTaskByProjectId(@PathVariable("userId") Long userId,
                                                       @PathVariable("projectId") Long projectId,
                                                       @PathVariable("taskId") Long taskId){

        Tasks tasks = taskService.getTask(userId,projectId,taskId);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tasks);
    }

    //태스크 삭제
    @DeleteMapping("users/{userId}/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<List<Tasks>> deleteTask(@PathVariable("userId") Long userId,
                                                  @PathVariable("projectId") Long projectId,
                                                  @PathVariable("taskId") Long taskId) {

        taskService.deleteTask(userId,projectId,taskId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    //태스크 내용 변경
    @PostMapping("users/{userId}/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<Tasks> modifyTaskContent(@PathVariable("userId") Long userId,
                                                   @PathVariable("projectId") Long projectId,
                                                   @PathVariable("taskId") Long taskId,
                                                   @RequestBody ModifyTaskDto modifyTaskDto) {

        Tasks tasks = taskService.modifyTask(userId,projectId,taskId, modifyTaskDto);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tasks);
    }
}
