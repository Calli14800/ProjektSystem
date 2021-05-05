package de.thbrandenburg.collin.webapp.professor;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessorSampleData {

    @Autowired
    private ProfessorRepository professorRepository;
    public void createProfessors(){
        Professor p = new Professor();
        p.setName("Nitze");
        p.setAusweisNr(123456789L);

        professorRepository.save( p );
    }

}
