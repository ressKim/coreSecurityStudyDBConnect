package com.study.corespringsecuritydbconnect.service;

import com.study.corespringsecuritydbconnect.domain.entity.Resources;

import java.util.List;

public interface ResourcesService {

    Resources getResources(long id);

    List<Resources> getResources();

    void createResources(Resources Resources);

    void deleteResources(long id);
}