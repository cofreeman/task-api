package com.nhnacademy.taskApi.controller;


import com.nhnacademy.taskApi.dto.TagCreateDto;
import com.nhnacademy.taskApi.dto.TagNameDto;
import com.nhnacademy.taskApi.entity.Tags;
import com.nhnacademy.taskApi.service.ProjectService;
import com.nhnacademy.taskApi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final ProjectService projectService;


    //프로젝트의 태그 리스트 반환
    @GetMapping("users/{userId}/projects/{projectId}/tags")
    public ResponseEntity<List<Tags>> getTags(@PathVariable("userId") Long userId,
                                              @PathVariable("projectId") Long projectId){
        List<Tags> tags = tagService.findAllTagByProjectId(userId,projectId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tags);
    }

    //프로젝트에 태그 추가
    @PostMapping("users/{userId}/projects/{projectId}/tags")
    public ResponseEntity<Tags> createTag(@PathVariable("userId") Long userId,
                                          @PathVariable("projectId") Long projectId,
                                          @RequestBody TagCreateDto tagCreateDto) {
        Tags tag = tagService.createTag(userId, projectId, tagCreateDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(tag);
    }

    // 태그명 변경
    @PostMapping("users/{userId}/projects/{projectId}/tags/{tagId}")
    public ResponseEntity<Tags> modifyTagName(@PathVariable("userId") Long userId,
                                              @PathVariable("projectId") Long projectId,
                                              @PathVariable("tagId") Long tagId,
                                              @RequestBody TagNameDto tagNameDto) {

        Tags tags = tagService.modifyTagName(userId,projectId,tagId, tagNameDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tags);
    }
}
