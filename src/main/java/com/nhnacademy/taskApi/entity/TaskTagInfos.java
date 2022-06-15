package com.nhnacademy.taskApi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "task_tag_infos")
@NoArgsConstructor
@Getter
@Setter
public class TaskTagInfos {

    @EmbeddedId
    private Pk pk;

    @MapsId("tagId")
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tags tags;

    @MapsId("taskId")
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks tasks;


    //question: noarg allarg
    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {

        private Long tagId;
        private Long taskId;
    }
}
