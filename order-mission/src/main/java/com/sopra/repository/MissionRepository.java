package com.sopra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sopra.entity.Mission;

public interface MissionRepository  extends CrudRepository<Mission, Integer> {

	
		@Query(value="SELECT p.`id_mission`, p.`collab_first_name`, p.`created_by`, p.`date`, p.`status`, p.`id_project` FROM Mission p LEFT JOIN user_project up ON p.id_project= up.project_id_project LEFT JOIN user u ON up.user_id_user = u.id_user WHERE u.name = :name", nativeQuery=true)
	    public List<Mission> find(@Param("name") String uName);
		 

}
