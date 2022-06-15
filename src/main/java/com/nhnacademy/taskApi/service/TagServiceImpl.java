package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.TagCreateDto;
import com.nhnacademy.taskApi.dto.TagNameDto;
import com.nhnacademy.taskApi.entity.Projects;
import com.nhnacademy.taskApi.entity.Tags;
import com.nhnacademy.taskApi.exception.NotFoundProjectException;
import com.nhnacademy.taskApi.exception.NotFoundProjectMemberException;
import com.nhnacademy.taskApi.exception.NotFoundTagException;
import com.nhnacademy.taskApi.repository.ProjectMembersRepository;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import com.nhnacademy.taskApi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMembersRepository projectMembersRepository;

    @Override
    public Tags modifyTagName(Long userId, Long projectId, Long tagId, TagNameDto tagNameDto){
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        Tags tags = tagRepository.findById(tagId).orElseThrow(NotFoundTagException::new);
        tags.setTagName(tagNameDto.getTagName());
        return tagRepository.save(tags);
    }

    @Override
    public List<Tags> findAllTagByProjectId(Long userId, Long projectId) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        return tagRepository.findAllTagsByProjectId(projectId);
    }

    @Override
    public Tags createTag(Long userId, Long projectId, TagCreateDto tagCreateDto) {
        projectMembersRepository.findByUserIdAndProjectsId(userId, projectId)
                .orElseThrow(NotFoundProjectMemberException::new);
        Projects projects = projectRepository.findById(projectId).orElseThrow(NotFoundProjectException::new);
        Tags tags = new Tags(projects, tagCreateDto);
        return tagRepository.save(tags);
    }
}
