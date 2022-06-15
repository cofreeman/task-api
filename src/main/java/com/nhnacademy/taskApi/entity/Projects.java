package com.nhnacademy.taskApi.entity;


import com.nhnacademy.taskApi.dto.ProjectRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Setter
@Getter
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_state")
    @Enumerated(EnumType.STRING)
    private ProjectState projectState;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "id")
    private String id;

    public Projects(Long userId,ProjectRequestDto projectRequestDto) {
        this.projectName = projectRequestDto.getProjectName();
        this.projectState = ProjectState.JOIN;
        this.userId = userId;
        this.id = projectRequestDto.getId();

    }
}
