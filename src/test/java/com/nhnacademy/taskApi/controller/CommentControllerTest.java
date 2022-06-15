package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.CommentCreateDto;
import com.nhnacademy.taskApi.entity.Comments;
import com.nhnacademy.taskApi.entity.Tasks;
import com.nhnacademy.taskApi.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.xml.stream.events.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@DisplayName("CommentController 테스트")
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CommentController(commentService))
                        .addFilters(new CharacterEncodingFilter("UTF-8", true)) // utf-8 필터 추가
                        .build();
    }
    private static final String comment = "댓글입니다.";
    private Comments comments = new Comments(1L,new Tasks(),new CommentCreateDto(comment));
    @DisplayName("코멘트 생성")
    @Test
    void createComment() throws Exception {
        //given
        given(commentService.createComment(any(),any(),any(),any()))
                .willReturn(comments);
        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/users/1/projects/1/tasks/1/comments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(
                                        "{" +
                                                "\"commentContent\":\""+comment+"\""
                                                + "}"));

        //then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("commentContent").value(comment));

    }
    @DisplayName("코멘트 전체 조회")
    @Test
    void getAllCommentByTaskId() throws Exception {
        //given
        given(commentService.findAllCommentByTaskId(any(),any(),any()))
                .willReturn(List.of(comments));
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/users/1/projects/1/tasks/1/comments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk());


    }
    @DisplayName("코멘트 단건 조회")
    @Test
    void getComment() throws Exception {
        //given
        given(commentService.getComment(any(),any(),any(),any()))
                .willReturn(comments);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/users/1/projects/1/tasks/1/comments/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk());

    }
    @DisplayName("코멘트 내용 변경")
    @Test
    void modifyComment() throws Exception {
        final String modifiedComment = "modifiedComment";
        //given
        comments.setCommentContent(modifiedComment);
        given(commentService.modifyComment(any(),any(),any(),any(),any()))
                .willReturn(comments);
        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/users/1/projects/1/tasks/1/comments/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(
                                        "{" +
                                                "\"commentContent\":\""+modifiedComment+"\""
                                                + "}"));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("commentContent").value(modifiedComment));
    }
    @DisplayName("코멘트 삭제")
    @Test
    void deleteComment() throws Exception {
        //given
        doNothing().when(commentService).deleteComment(1L);
        //when
        ResultActions actions =
                mockMvc.perform(
                        delete("/users/1/projects/1/tasks/1/comments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk());
    }
}