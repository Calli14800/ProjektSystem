package com.example.springaufgabe;

import com.example.springaufgabe.Student.Student;
import com.example.springaufgabe.Student.StudentSampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RunSampleDataCreation implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private StudentSampleData studentSampleData;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        studentSampleData.createStudents();
    }
}
