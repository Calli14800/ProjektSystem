package de.thbrandenburg.collin.webapp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Student extends Person {
   private double studentNumber;

   public Student(String name) {
      super(name);
   }

   public Student() {

   }

   public void isEligibleToEnroll() {

   }

   public void getSeminarHistory() {

   }

   public void takeExamination() {

   }
}
