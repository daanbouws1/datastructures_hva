package Comparatos;


import java.util.Comparator;
import models.LinkedList;
import models.Student;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author REMCO
 */
public class StudentComparators {
    /**
     * Comparator op basis van studentnummer
     */
    public static Comparator<Student> compareStudentnr = (Student a, Student b) -> a.getStudentNumber()- b.getStudentNumber();

    /**
     * Comparator op basis van klas
     */
    public static Comparator<LinkedList<Student>> compareKlas = (LinkedList<Student> a, LinkedList<Student> b) -> {
        if ((a.size() <= 0) || (b.size() <= 0)) {
            return 0;
        }
        
        return a.get(0).getKlas().compareTo(b.get(0).getKlas());
    };
}