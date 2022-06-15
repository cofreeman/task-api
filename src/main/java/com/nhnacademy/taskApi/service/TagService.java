package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.TagCreateDto;
import com.nhnacademy.taskApi.dto.TagNameDto;
import com.nhnacademy.taskApi.entity.Tags;

import java.util.List;

public interface TagService {
    Tags createTag(Long userId, Long projectId, TagCreateDto tagCreateDto);

    Tags modifyTagName(Long userId, Long projectId, Long tagId, TagNameDto tagNameDto);

    List<Tags> findAllTagByProjectId(Long userId, Long projectId);
}
