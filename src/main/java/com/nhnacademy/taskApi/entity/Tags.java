package com.nhnacademy.taskApi.entity;

import com.nhnacademy.taskApi.dto.TagCreateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tags")
@NoArgsConstructor
@Setter
@Getter
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects projects;

    @Column(name = "tag_name")
    private String tagName;

    public Tags(Projects projects, TagCreateDto tagCreateDto) {
        this.projects = projects;
        this.tagName = tagCreateDto.getTagName();
    }
}
