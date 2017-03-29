package com.sopra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sopra.entity.Mission;

public interface MissionRepository  extends CrudRepository<Mission, Integer> {



}
