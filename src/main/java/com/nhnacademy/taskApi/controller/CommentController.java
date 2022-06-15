package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.CommentCreateDto;
import com.nhnacademy.taskApi.dto.CommentModifyDto;
import com.nhnacademy.taskApi.entity.Comments;
import com.nhnacademy.taskApi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Project 멤버는 Task 에 Comment 를 생성할 수 있습니다.
//Comment 를 생성한 사용자는 Comment 를 수정, 삭제 할 수 있습니다.
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    //코멘트 추가 (코멘트 달기)
    @PostMapping("users/{userId}/projects/{projectId}/tasks/{taskId}/comments")
    public ResponseEntity<Comments> createComment(@PathVariable("userId") Long userId,
                                                  @PathVariable("projectId") Long projectId,
                                                  @PathVariable("taskId") Long taskId,
                                                  @RequestBody CommentCreateDto commentCreateDto) {
        Comments comments = commentService.createComment(userId, projectId, taskId, commentCreateDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(comments);
    }

    // 코멘트 전부 보기 ( task 의 댓글 목록 출력용)
    @GetMapping("users/{userId}/projects/{projectId}/tasks/{taskId}/comments")
    public ResponseEntity<List<Comments>> getAllCommentByTaskId(@PathVariable("userId") Long userId,
                                                                @PathVariable("projectId") Long projectId,
                                                                @PathVariable("taskId") Long taskId) {

        List<Comments> comments = commentService.findAllCommentByTaskId(userId, projectId, taskId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(comments);
    }

    // 코멘트 단건 보기(수정할 때 placeholder 로 띄울라고)
    @GetMapping("users/{userId}/projects/{projectId}/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<Comments> getComment(@PathVariable("userId") Long userId,
                                               @PathVariable("projectId") Long projectId,
                                               @PathVariable("taskId") Long taskId,
                                               @PathVariable("commentId") Long commentId) {

        Comments comments = commentService.getComment(userId,projectId,taskId,commentId);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(comments);
    }

    //코멘트 수정
    @PostMapping("users/{userId}/projects/{projectId}/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<Comments> modifyComment(@PathVariable("userId") Long userId,
                                                  @PathVariable("projectId") Long projectId,
                                                  @PathVariable("taskId") Long taskId,
                                                  @PathVariable("commentId") Long commentId,
                                                  @RequestBody CommentModifyDto commentModifyDto) {

        Comments comments = commentService.modifyComment(userId,projectId,taskId,commentId, commentModifyDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(comments);
    }

    //코멘트 삭제
    @DeleteMapping("/projects/{id}/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<Comments> deleteComment(@PathVariable("commentId") Long commentId) {

        commentService.deleteComment(commentId);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .build();
    }
}
