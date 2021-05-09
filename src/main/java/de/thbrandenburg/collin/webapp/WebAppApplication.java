package de.thbrandenburg.collin.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.xml.bind.api.impl.NameConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class WebAppApplication {

	StandardServiceRegistry ssr;

	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

	@PostMapping("/students")
	public String createPerson(@RequestParam(value = "firstName") String firstName) {
		Student student = new Student(firstName);
		student.setAge(25);
		student.setLastName("Mueller");
		student.setPhoneNumber("1234567");
		student.setEmailAddress("pascal.mueller@gmx.de");

	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
		.configure()
		.build();

	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
	SessionFactory factory = meta.getSessionFactoryBuilder().build();
	Session session = factory.openSession();

	session.beginTransaction();
	session.persist(student);
	session.flush();




		return "Studierende(r) wurde erfolgreich in die Datenbank persistiert!";
	}

	@GetMapping("/students")
	public String viewStudents() {
		ArrayList<Student> students = new ArrayList<>(100);

		ssr = new StandardServiceRegistryBuilder()
				.configure()
				.build();

		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		session.beginTransaction();
		Student student = session.load(Student.class, 1L);
		session.flush();

		String jsonString = null;

		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			jsonString = om.writeValueAsString(student);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}



}
