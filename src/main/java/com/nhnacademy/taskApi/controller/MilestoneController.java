package com.nhnacademy.taskApi.controller;


import com.nhnacademy.taskApi.dto.MilestoneCreateDto;
import com.nhnacademy.taskApi.dto.MilestoneModifyDto;
import com.nhnacademy.taskApi.entity.Milestones;
import com.nhnacademy.taskApi.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    // 마일스톤 생성
    @PostMapping("users/{userId}/projects/{projectId}/milestones")
    public ResponseEntity<Milestones> getProject(@PathVariable("userId") Long userId,
                                                 @PathVariable("projectId") Long projectId,
                                                 @RequestBody MilestoneCreateDto milestoneCreateDto) {

        Milestones milestones = milestoneService.createMilestone(userId,projectId, milestoneCreateDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(milestones);
    }

    //마일스톤리스트 반환
    @GetMapping("users/{userId}/projects/{projectId}/milestones")
    public ResponseEntity<List<Milestones>> findAllMilestoneByProjectId(@PathVariable("userId") Long userId,
                                                                        @PathVariable("projectId") Long projectId) {


        List<Milestones> milestones = milestoneService.findAllMilestoneByProjectId(userId,projectId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(milestones);
    }

    //마일스톤 단건 조회 ( 마일스톤 정보)
    @GetMapping("users/{userId}/projects/{projectId}/milestones/{milestoneId}")
    public ResponseEntity<Milestones> getMilestoneById(@PathVariable("userId") Long userId,
                                                       @PathVariable("projectId") Long projectId,
                                                       @PathVariable("milestoneId") Long milestoneId) {
        Milestones milestones = milestoneService.getMilestoneById(userId,projectId,milestoneId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(milestones);
    }

    //마일스톤 삭제 ( 마일스톤 정보)
    @DeleteMapping("users/{userId}/projects/{projectId}/milestones/{milestoneId}")
    public ResponseEntity<Milestones> deleteMilestoneById(@PathVariable("userId") Long userId,
                                                          @PathVariable("projectId") Long projectId,
                                                          @PathVariable("milestoneId") Long milestoneId) {
        milestoneService.deleteMilestoneById(userId,projectId,milestoneId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .build();
    }

    //마일스톤 수정
    @PostMapping("users/{userId}/projects/{projectId}/milestones/{milestoneId}")
    public ResponseEntity<Milestones> modifyMilestoneById(@PathVariable("userId") Long userId,
                                                          @PathVariable("projectId") Long projectId,
                                                          @PathVariable("milestoneId") Long milestoneId,
                                                          @RequestBody MilestoneModifyDto milestoneModifyDto) {
        Milestones milestones = milestoneService.modifyMilestoneById(userId,projectId,milestoneId,milestoneModifyDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(milestones);
    }
}
