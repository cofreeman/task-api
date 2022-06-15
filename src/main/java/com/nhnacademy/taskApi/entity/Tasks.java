package com.nhnacademy.taskApi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects projects;

    @OneToOne
    @JoinColumn(name = "milestone_id")
    private Milestones milestones;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_content")
    private String taskContent;

    public Tasks(Projects projects, Milestones milestones, String taskTitle, String taskContent) {
        this.projects = projects;
        this.milestones = milestones;
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
    }
}
