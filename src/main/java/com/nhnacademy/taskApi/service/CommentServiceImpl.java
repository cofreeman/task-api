package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.CommentCreateDto;
import com.nhnacademy.taskApi.dto.CommentModifyDto;
import com.nhnacademy.taskApi.entity.Comments;
import com.nhnacademy.taskApi.entity.Tasks;
import com.nhnacademy.taskApi.exception.NotFoundCommentException;
import com.nhnacademy.taskApi.exception.NotFoundProjectMemberException;
import com.nhnacademy.taskApi.exception.NotFoundTasksException;
import com.nhnacademy.taskApi.repository.CommentRepository;
import com.nhnacademy.taskApi.repository.ProjectMembersRepository;
import com.nhnacademy.taskApi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final ProjectMembersRepository projectMembersRepository;

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }


    @Override
    public Comments createComment(Long userId, Long projectId, Long taskId, CommentCreateDto commentCreateDto) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        Tasks tasks = taskRepository.findById(taskId).orElseThrow(NotFoundTasksException::new);
        //todo: 사용자 정보는 이름은 어떻게 띄울까요...
        Comments comments = new Comments(userId, tasks, commentCreateDto);
        return commentRepository.save(comments);
    }

    @Override
    public List<Comments> findAllCommentByTaskId(Long userId, Long projectId, Long taskId) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        return commentRepository.findAllCommentByTaskId(taskId);
    }

    @Override
    public Comments modifyComment(Long userId, Long projectId, Long taskId, Long commentId, CommentModifyDto commentModifyDto) {
        Comments comments = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        comments.setCommentContent(commentModifyDto.getCommentContent());
        return commentRepository.save(comments);
    }

    @Override
    public Comments getComment(Long userId, Long projectId, Long taskId, Long commentId) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        return commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);
    }
}
