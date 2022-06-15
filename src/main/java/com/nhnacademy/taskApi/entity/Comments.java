package com.nhnacademy.taskApi.entity;

import com.nhnacademy.taskApi.dto.CommentCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks tasks;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "user_id")
    private Long userId;

    private String id;

    public Comments(Long userId, Tasks tasks, CommentCreateDto commentCreateDto) {
        this.tasks = tasks;
        this.commentContent = commentCreateDto.getCommentContent();
        this.userId = userId;
        this.id = "임시 아이디";
    }
}
