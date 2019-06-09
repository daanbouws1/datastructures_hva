/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.*;

import models.Student;
import nl.hva.dmci.ict.se.datastructures.KlasGenerator;

/**
 *
 * @author Daan
 */
public class BucketSort {

    private static ArrayList<ArrayList<Student>> buckets;
    private static ArrayList<Student> bucket;
    private static InsertionSort sorter;

    public static ArrayList<ArrayList<Student>> sort(List<Student> students, String[] klassen) {
        buckets = new ArrayList<>();
        sorter = new InsertionSort();

        for (String klas : klassen) {
            bucket = new ArrayList<>();
            students.stream().filter((student) -> (student.getKlas().equals(klas))).forEach((student) -> {
                bucket.add(student);
            });
            sorter.insertionSort(bucket);
            buckets.add(bucket);
        }
        return buckets;
    }
}
