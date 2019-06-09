package opdracht2;

import Algorithms.BucketSort;
import Algorithms.InsertionSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import models.Student;
import nl.hva.dmci.ict.se.datastructures.KlasGenerator;
import nl.hva.dmci.ict.se.datastructures.util.Generator;
import nl.hva.dmci.ict.se.datastructures.util.Stopwatch;

/**
 *
 * @author Daan
 */
public class Opdracht2 {

    private static final int AANSTAL_STUDENTEN = 50;
    private static ArrayList<Student> students = new ArrayList<>();
    private static InsertionSort sort;
    private static Stopwatch timer = new Stopwatch();
    private static Generator gen = new Generator(AANSTAL_STUDENTEN);
    private static String[] klassen;


    public static void main(String[] args) {
        students = gen.generateStudents();

        klassen = KlasGenerator.maakKlassen(students.size());
        klassen = new HashSet<>(Arrays.asList(klassen)).toArray(new String[0]);

        int i = 0;

        for (Student student : students) {
            if (i == klassen.length) i = 0;
            student.setKlas(klassen[i]);
            i++;
        }

//        for (Student student: students) {
//            System.out.println(student);
//        }

        insertionSort();
        bucketSort();
    }

    private static void insertionSort() {
        timer.startStopwatch();
        sort = new InsertionSort();
        ArrayList<Student> result = sort.insertionSort2(students);

        timer.stopStopwatch();
        for (Student s: result) {
            System.out.println(s.toString());
        }
        System.out.println(timer.getTime());
    }

    private static void bucketSort() {
        timer.startStopwatch();

        ArrayList<ArrayList<Student>> sorteerdeStudenten = BucketSort.sort(students, klassen);

        timer.stopStopwatch();

        System.out.println(isStijgend(students));

        for (ArrayList<Student> bucket : sorteerdeStudenten) {
//            System.out.println(bucket);
            for (Student student : bucket) {
//                System.out.println(student);
            }
        }
        System.out.println(timer.getTime());
    }

    //werrukt nie
    public static <T extends Comparable<T>> boolean isStijgend(List<T> rij) {
        boolean result = false;
        return rij.get(0).compareTo(rij.get(1)) < 0 ? (rij.size() <= 2 ? result = true : isStijgend(rij.subList(1, rij.size()))) : result;
    }
}
