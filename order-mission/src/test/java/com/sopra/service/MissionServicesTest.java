package com.sopra.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.sopra.entity.Accommodation;
import com.sopra.entity.Itinerary;
import com.sopra.entity.Mission;
import com.sopra.entity.Project;
import com.sopra.entity.Rent;
import com.sopra.entity.Role;
import com.sopra.repository.MissionRepository;
import com.sopra.repository.ProjectRepository;
import com.sopra.repository.RoleRepository;
import com.sopra.service.impl.ServicesImp;

@RunWith(SpringRunner.class)
public class MissionServicesTest {
	
	@Mock
	private RoleRepository roleRepository;

	@Mock
	private MissionRepository missionRepository;
	
	@Mock
	private ProjectRepository projectRepository;
	
	@InjectMocks
	private ServicesImp servicesImp;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	
	
	@Test
	public void TestGetRoleById ()
	{
		Role role = new Role(70, "Director");
		when(roleRepository.findOne(70)).thenReturn(role);
		Role result = servicesImp.getRole(70);
		assertEquals(70, result.getId());
		assertEquals("Director", result.getRol());
	}
	
	@Test
	public void TestSaveMission()
	{
		Project proj = new Project();
		proj.setNameProj("Mercadoona");
		proj.setId(1);
		proj.setAgency("Valencia");
		proj.setDivision("Valencia");
		
		Mission mission = new Mission(1, "Adrian", null, "Abierta", "apelaez", proj, null, null, null);
		
		when(missionRepository.save(mission)).thenReturn(mission);
		
		Mission result = servicesImp.saveMissionForTest(mission);
		
	

	}

}
