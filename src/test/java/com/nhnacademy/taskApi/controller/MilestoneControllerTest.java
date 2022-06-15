package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.service.MilestoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest(MilestoneController.class)
@DisplayName("MilestoneController 테스트")
class MilestoneControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MilestoneService milestoneService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new MilestoneController(milestoneService))
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) // utf-8 필터 추가
                .build();
    }

    @DisplayName("프로젝트 생성 테스트")
    @Test
    void getProject() {

    }
    @DisplayName("")
    @Test
    void findAllMilestoneByProjectId() {
    }
    @DisplayName("")
    @Test
    void getMilestoneById() {
    }
    @DisplayName("")
    @Test
    void deleteMilestoneById() {
    }
    @DisplayName("")
    @Test
    void modifyMilestoneById() {
    }
}