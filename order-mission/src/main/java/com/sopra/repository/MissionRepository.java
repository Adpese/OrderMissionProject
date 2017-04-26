package com.sopra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sopra.entity.Mission;

public interface MissionRepository  extends CrudRepository<Mission, Integer> {

	
		@Query(value="(SELECT p.`id_mission`, p.`collab_first_name`, p.`created_by`, p.`date`, p.`status`, p.`id_project` FROM Mission p LEFT JOIN user_project up ON p.id_project= up.project_id_project LEFT JOIN user u ON up.user_id_user = u.id_user WHERE u.name = :name) UNION (select m2.`id_mission`, m2.`collab_first_name`, m2.`created_by`, m2.`date`, m2.`status`, m2.`id_project`  from Mission m2 where m2.created_By = :name)", nativeQuery=true)
	    public List<Mission> find(@Param("name") String uName);
		

		@Query(value="(SELECT m.`id_mission`, m.`collab_first_name`, m.`created_by`, m.`date`, m.`status`, m.`id_project` FROM Mission m LEFT JOIN project p ON m.id_project= p.id_project WHERE p.agency = :agency) UNION (select m2.`id_mission`, m2.`collab_first_name`, m2.`created_by`, m2.`date`, m2.`status`, m2.`id_project`  from Mission m2 where m2.created_By = :userName)", nativeQuery=true)
	    public List<Mission> findDirector(@Param("agency") String agency,  @Param("userName") String userName);
		@Query(value="(SELECT m.`id_mission`, m.`collab_first_name`, m.`created_by`, m.`date`, m.`status`, m.`id_project` FROM Mission m LEFT JOIN project p ON m.id_project= p.id_project WHERE p.agency = :agency) UNION (select m2.`id_mission`, m2.`collab_first_name`, m2.`created_by`, m2.`date`, m2.`status`, m2.`id_project`  from Mission m2 where m2.created_By = :userName)", nativeQuery=true)		
	    public List<Mission> findAssistant(@Param("agency") String agency, @Param("userName") String userName);
		
		@Query(value="(SELECT p.`name_proj` FROM Project p LEFT JOIN user_project up ON p.id_project= up.project_id_project LEFT JOIN user u ON up.user_id_user = u.id_user WHERE u.name = :name)", nativeQuery=true)
	    public List<String> findProyectsJefe(@Param("name") String uName);
		 
		
		
		
		
		List<Mission> findBycreatedBy(String lastname);

}
