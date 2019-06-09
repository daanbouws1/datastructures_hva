/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;

import models.LinkedList;
import models.Student;

/**
 *
 * @author REMCO
 */
public class InsertionSort {
    public InsertionSort(){
    }
    
    public void insertionSort(ArrayList<Student> students){
        int i, j;
        Student key;
        for(i = 1; i < students.size(); i++) {
            for(j = i; j > 0; j--) {
                key = students.get(j);
                if(key.compareTo(students.get(j-1)) == -1) {
                    students.set(j, students.get(j-1));
                    students.set(j-1, key);
                }
            }
        }
    }

    public ArrayList<Student> insertionSort2(ArrayList<Student> students){
        int i, j;
        Student key;
        for(i = 1; i < students.size(); i++) {
            for(j = i; j > 0; j--) {
                key = students.get(j);
                if(key.compareTo(students.get(j-1)) == -1) {
                    students.set(j, students.get(j-1));
                    students.set(j-1, key);
                }
            }
        }
        return students;
    }
}
