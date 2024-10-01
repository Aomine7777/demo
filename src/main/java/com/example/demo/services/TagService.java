package com.example.demo.services;

import com.example.demo.models.Tag;
import com.example.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

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
