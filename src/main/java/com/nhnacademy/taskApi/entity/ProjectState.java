package com.nhnacademy.taskApi.entity;


import lombok.Getter;

@Getter
public enum ProjectState {
    JOIN("활성"),
    RESTING("휴면"),
    DELETE("종료");

    ProjectState(String state) {
        this.state = state;
    }

    private final String state;

}
