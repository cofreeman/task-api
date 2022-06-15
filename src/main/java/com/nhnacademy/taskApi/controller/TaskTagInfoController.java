//package com.nhnacademy.taskApi.controller;
//
//
//import com.nhnacademy.taskApi.dto.TaskTagInfoCreateDto;
//import com.nhnacademy.taskApi.entity.Projects;
//import com.nhnacademy.taskApi.entity.Tags;
//import com.nhnacademy.taskApi.service.TaskTagInfoService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class TaskTagInfoController {
//
//    private final TaskTagInfoService taskTagInfoService;
//
//    //task에 태그 추가(여기서 create)
//    @PostMapping("users/{userId}/projects/{projectId}/tasks/{taskId}/taskTagInfos")
//    public ResponseEntity<Tags> createTaskTagInfo(@PathVariable("taskId") Long taskId,
//                                                  @RequestBody TaskTagInfoCreateDto taskTagInfoCreateDto){
//        //todo: path어떻게 구성해야
//        taskTagInfoService.createTaskTagInfo(taskId,taskTagInfoCreateDto);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .headers(httpHeaders)
//                .body(tag);
//    }
//
//    // task에 달려있는 모든 태그 조회
//    @PostMapping("users/{userId}/projects/{projectId}/tags")
//    public ResponseEntity<Tags> createTag(@RequestBody TagRequestDto tagRequestDto,
//                                          @PathVariable("id") Long id){
//        Projects projects = projectService.findById(id);
//        Tags tag = tagService.createTag(projects,tagRequestDto);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .headers(httpHeaders)
//                .body(tag);
//    }
//}
