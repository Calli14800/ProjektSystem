package de.thbrandenburg.collin.webapp;

import de.thbrandenburg.collin.webapp.professor.Professor;
import de.thbrandenburg.collin.webapp.professor.ProfessorRepository;
import de.thbrandenburg.collin.webapp.professor.ProfessorSampleData;
import de.thbrandenburg.collin.webapp.student.Student;
import de.thbrandenburg.collin.webapp.student.StudentRepository;
import de.thbrandenburg.collin.webapp.student.StudentSampleData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class WebAppApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private StudentSampleData studentSampleData;
	@Autowired
	private ProfessorSampleData professorSampleData;

	@BeforeEach
	public void setupData() {
		studentSampleData.createStudents();
		professorSampleData.createProfessors();

	}


	@AfterTestExecution
	public void tearDown() {
		studentRepository.deleteAll();
		professorRepository.deleteAll();
	}




	@Test
	void contextLoads() {
	}

	@Test
	public	void	testProfAndStudent() {
		Optional<Student> sOpt = studentRepository.findById( 1L );
		assertFalse( sOpt.isEmpty() );
		Student pascal = sOpt.get();
		assertEquals("Pascal", pascal.getName() );
		assertEquals( 12345678L, pascal.getMatrNr() );


	}

	@Test
	public void testHasProf1TheNameNitzeAndHasCorrectausweisNr() {
		Optional<Professor> pOpt = professorRepository.findById( 2L);
		assertFalse( pOpt.isEmpty() );
		Professor nitze = pOpt.get();
		assertEquals("Nitze", nitze.getName() );
		assertEquals( 123456789L, nitze.getAusweisNr() );

	}
}
