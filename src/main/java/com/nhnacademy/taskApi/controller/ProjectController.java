package com.nhnacademy.taskApi.controller;


import com.nhnacademy.taskApi.dto.ProjectRequestDto;
import com.nhnacademy.taskApi.entity.Projects;
import com.nhnacademy.taskApi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // 해당 프로젝트의 정보 불러오기
    @GetMapping("users/{userId}/projects/{projectId}")
    public ResponseEntity<Projects> getProject(@PathVariable("projectId") Long projectId) {

        Projects projects = projectService.findById(projectId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(projects);
    }

    //참여하고 있는 프로젝트 리스트 반환
    @GetMapping("users/{userId}/projects")
    public ResponseEntity<List<Projects>> getProjects(@PathVariable("userId") Long userId) {
        List<Projects> projects = projectService.findAllProjectByUserId(userId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(projects);
    }

    @PostMapping("users/{userId}/projects")
    public ResponseEntity<Projects> createProject(@PathVariable("userId") Long userId,
                                                  @RequestBody ProjectRequestDto projectRequestDto) {
        Projects projects = projectService.createProject(userId, projectRequestDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(projects);
    }

    @PostMapping("users/{userId}/projects/{projectId}")
    public ResponseEntity<Projects> modifyProject(@PathVariable("userId") Long userId,
                                                  @PathVariable("projectId") Long projectId,
                                                  @RequestBody ProjectRequestDto projectRequestDto) {
        //todo: 해당 프로젝트에 멤버가 참여하지 않는 경우 수정불가
        Projects projects = projectService.modifyProject(userId, projectId, projectRequestDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(projects);
    }

}
