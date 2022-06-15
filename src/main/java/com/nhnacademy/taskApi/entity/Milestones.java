package com.nhnacademy.taskApi.entity;

import com.nhnacademy.taskApi.dto.MilestoneCreateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "milestones")
@NoArgsConstructor
@Getter
@Setter
public class Milestones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id")
    private Long milestoneId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects projectId;

    @Column(name = "milestone_name")
    private String milestoneName;

    @Column(name = "start_data")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public Milestones(Projects projects, MilestoneCreateDto milestoneCreateDto) {
        this.projectId = projects;
        this.milestoneName = milestoneCreateDto.getMilestoneName();
        this.startDate = milestoneCreateDto.getStartDate();
        this.endDate = milestoneCreateDto.getEndDate();
    }
}
