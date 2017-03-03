package com.sopra;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestResource
@RequestMapping(produces = "application/json")
public interface CollaboraterRepository  extends CrudRepository<Collaborater, Integer> {
	//List<TaKK> findByTaskArchived(@Param("archivedfalse") int taskArchivedFalse);
	//List<TaKK> findByProject(@Param("status") String project);
	
	

}
