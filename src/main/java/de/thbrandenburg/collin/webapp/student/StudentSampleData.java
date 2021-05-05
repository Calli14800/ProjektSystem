package de.thbrandenburg.collin.webapp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentSampleData {
    @Autowired
    private StudentRepository studentRepository;
    public void createStudents() {
        Student s = new Student();
        s.setName("Pascal");
        s.setMatrNr( 12345678L );

        studentRepository.save( s );
    }
}
