/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures.util;

import java.util.*;

import models.Student;
import nl.hva.dmci.ict.se.datastructures.KlasGenerator;

/**
 *
 * @author remco
 */
public class Generator {

    private final int STARTING_ID = 500600001;
    private final int DEFAULT_NUMBER_OF_STUDENS = 10000;
    private int numberOfStudents;
    private ArrayList<Student> students;
    
    public Generator() {
        this.numberOfStudents = DEFAULT_NUMBER_OF_STUDENS;
    }
    public Generator(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
    
    public ArrayList<Student> generateStudents() {
        students = new ArrayList<>();
        int id = STARTING_ID;

        for(int i = 0; i < numberOfStudents; i++) {
            Random random = new Random();
            double grade = (random.nextInt(91) + 10) / 10.0;
            students.add(new Student(id++, grade));
        }

        return students;
    }
}
