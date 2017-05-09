package com.sopra.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void TestGetRoleById() {
		Role role = new Role(70, "Director");
		when(roleRepository.findOne(70)).thenReturn(role);
		Role result = servicesImp.getRole(70);
		assertEquals(70, result.getId());
		assertEquals("Director", result.getRol());
	}

	@Test
	public void TestSaveMission() {
		Project proj = new Project();
		proj.setNameProj("Mercadoona");
		proj.setId(1);
		proj.setAgency("Valencia");
		proj.setDivision("Valencia");

		Accommodation accommodation = new Accommodation(1, "hotel", "10-04-2017", "11-04-2017");
		Itinerary itinerary = new Itinerary(1, "10-04-2017", "Valencia", "Madrid", "10:50", "20:00", "Avion", "Iberia",300);
		Rent rent = new Rent(1, "Adrian", "10-04-2017", "10:10", "Valencia", "11-04-2017", "10:10", "Valencia", 300);
		
		List<Accommodation> accList = new ArrayList<Accommodation>();
		accList.add(accommodation);
		
		List<Itinerary> itList = new ArrayList<Itinerary>();
		itList.add(itinerary);
		
		List<Rent> rentList = new ArrayList<Rent>();
		rentList.add(rent);

		Mission mission = new Mission(1, "Adrian", null, "Abierta", "apelaez", proj, itList, accList, rentList);

		when(missionRepository.save(mission)).thenReturn(mission);

		Mission result = servicesImp.saveMissionForTest(mission);

		assertEquals(1, result.getId());
		assertEquals("Adrian", result.getCollabFirstName());
		assertEquals("Abierta", result.getStatus());
		assertEquals("apelaez", result.getCreatedBy());
		assertEquals("Mercadoona", result.getProject().getNameProj());
		
		assertEquals(1, result.getProject().getId());
		assertEquals("Valencia", result.getProject().getAgency());
		assertEquals("Valencia", result.getProject().getDivision());
		
		assertEquals(1, result.getAccommodations().get(0).getId());
		assertEquals("hotel", result.getAccommodations().get(0).getHotelName());
		assertEquals("10-04-2017", result.getAccommodations().get(0).getEntryDate());
		assertEquals("11-04-2017", result.getAccommodations().get(0).getDepartureDate());
		assertEquals(0, result.getAccommodations().get(0).getNumberOfNights());

		assertEquals(1, result.getRents().get(0).getId());
		assertEquals("10-04-2017", result.getRents().get(0).getPickupDate());
		assertEquals("Adrian", result.getRents().get(0).getDriverName());
		assertEquals("10:10", result.getRents().get(0).getPickupHour());
		assertEquals("Valencia", result.getRents().get(0).getPickupPlace());
		assertEquals("11-04-2017", result.getRents().get(0).getDeliveryDate());
		assertEquals("10:10", result.getRents().get(0).getDeliveryHour());
		assertEquals("Valencia", result.getRents().get(0).getDeliveryPlace());
		assertEquals(300, result.getRents().get(0).getPrice());
		
		assertEquals(1, result.getItineraries().get(0).getId());
		assertEquals("10-04-2017", result.getItineraries().get(0).getDate());
		assertEquals("Valencia", result.getItineraries().get(0).getOrigin());
		assertEquals("Madrid", result.getItineraries().get(0).getDestination());
		assertEquals("10:50", result.getItineraries().get(0).getDepartureHour());
		assertEquals("20:00", result.getItineraries().get(0).getArrivalHour());
		assertEquals("Avion", result.getItineraries().get(0).getTransport());
		assertEquals("Iberia", result.getItineraries().get(0).getCompany());
		assertEquals(300, result.getItineraries().get(0).getPrice());
		
	}

}
