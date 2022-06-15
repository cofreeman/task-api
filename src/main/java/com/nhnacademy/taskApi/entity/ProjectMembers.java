package com.nhnacademy.taskApi.entity;

import com.nhnacademy.taskApi.dto.ProjectMemberCreateDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project_members")
@NoArgsConstructor
@Getter
@Setter
public class ProjectMembers {

    @EmbeddedId
    private Pk pk;

    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects projects;

    private String id;



    @Getter
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Setter
    public static class Pk implements Serializable {

        @Column(name = "user_id")
        private Long userId;
        private Long projectId;

    }

    public ProjectMembers(Projects projects, ProjectMemberCreateDto projectMemberCreateDto) {
        Pk pk = new Pk();
        pk.setProjectId(projects.getProjectId());
        pk.setUserId(projectMemberCreateDto.getUserId());
        this.pk = pk;
        this.projects = projects;
        this.id = projectMemberCreateDto.getId();

    }

    public ProjectMembers(Projects projects) {
        Pk pk = new Pk();
        pk.setProjectId(projects.getProjectId());
        pk.setUserId(projects.getUserId());
        this.pk = pk;
        this.projects = projects;
        this.id = projects.getId();
    }

}
