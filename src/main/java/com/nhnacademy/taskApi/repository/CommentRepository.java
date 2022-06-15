package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long> {

    @Query("select c from Comments c where c.tasks.taskId = :taskId")
    List<Comments> findAllCommentByTaskId(@Param("taskId") Long taskId);
}
