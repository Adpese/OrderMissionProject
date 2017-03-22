package com.sopra.repository;

import org.springframework.data.repository.CrudRepository;

import com.sopra.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

}
