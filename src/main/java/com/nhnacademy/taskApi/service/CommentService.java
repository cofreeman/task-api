package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.CommentCreateDto;
import com.nhnacademy.taskApi.dto.CommentModifyDto;
import com.nhnacademy.taskApi.dto.TaskCreateDto;
import com.nhnacademy.taskApi.entity.Comments;

import java.util.List;

public interface CommentService {

    void deleteComment(Long commentId);

    Comments createComment(Long userId, Long projectId, Long taskId, CommentCreateDto commentCreateDto);

    List<Comments> findAllCommentByTaskId(Long userId, Long projectId, Long taskId);

    Comments modifyComment(Long userId, Long projectId, Long taskId, Long commentId, CommentModifyDto commentModifyDto);

    Comments getComment(Long userId, Long projectId, Long taskId, Long commentId);
}
