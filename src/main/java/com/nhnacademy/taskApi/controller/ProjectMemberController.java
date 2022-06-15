package com.nhnacademy.taskApi.controller;


import com.nhnacademy.taskApi.dto.ProjectMemberCreateDto;
import com.nhnacademy.taskApi.entity.ProjectMembers;
import com.nhnacademy.taskApi.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    // 생성(플젝에 멤버 추가)
    @PostMapping("users/{userId}/projects/{projectId}/projectMembers")
    public ResponseEntity<ProjectMembers> createProjectMember(@PathVariable("userId") Long userId,
                                                              @PathVariable("projectId") Long projectId,
                                                              @RequestBody ProjectMemberCreateDto projectMemberCreateDto) {
        ProjectMembers projectMembers = projectMemberService.createProjectMember(userId, projectId, projectMemberCreateDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(projectMembers);
    }


    // 전체 조회(플젝 멤버 띄우기)
    @GetMapping("users/{userId}/projects/{projectId}/projectMembers")
    public ResponseEntity<List<ProjectMembers>> findAllProjectMemberByProjectId(@PathVariable("userId") Long userId,
                                                          @PathVariable("projectId") Long projectId) {
        List<ProjectMembers> projectMembers = projectMemberService.findAllProjectMembers(userId,projectId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(projectMembers);
    }

}
