package com.example.demo.services;

import com.example.demo.models.Tag;

import java.util.Set;


public interface TagService {

    Set<Tag> findOrCreateByNames(Set<String> tagNames);
}