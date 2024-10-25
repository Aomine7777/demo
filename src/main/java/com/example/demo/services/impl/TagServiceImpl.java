package com.example.demo.services.impl;

import com.example.demo.models.Tag;
import com.example.demo.repositories.TagRepository;
import com.example.demo.services.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public Set<Tag> findOrCreateByNames(Set<String> tagNames) {
        Set<Tag> tags = new HashSet<>();

        for (String tagName : tagNames) {

            Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                tagRepository.save(newTag);
                return newTag;
            });
            tags.add(tag);
        }
        return tags;
    }
}