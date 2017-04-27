package com.sopra.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.sopra.entity.Role;
import com.sopra.repository.RoleRepository;
import com.sopra.service.impl.ServicesImp;

@RunWith(SpringRunner.class)
public class MissionServicesTest {
	
	@Mock
	private RoleRepository roleRepository;

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

}
