import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.sopra.entity.Project;
import com.sopra.repository.MissionRepository;
import com.sopra.repository.ProjectRepository;

public class test {
	
	@Mock
	MissionRepository missionRepository;
	
	@Mock
	ProjectRepository projectRepository;

	@Test
	public void test() {
		Project proj = new Project();
		proj.setNameProj("Mercadoona");
		proj.setId(1);
		proj.setAgency("Valencia");
		proj.setDivision("Valencia");
		
		Project result = new Project();
		result.setId(1);
		projectRepository.findOne(result.getId());
		
		when(projectRepository.findOne(1)).thenReturn(proj);
		assertEquals( ,result.compareTo(9));

		
	}

}
