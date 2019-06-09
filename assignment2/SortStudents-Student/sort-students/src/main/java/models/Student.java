package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author remco
 */
public class Student implements Comparable<Student> {

    private int studentNumber;
    private double grade;
    private String klas;

    public Student() {
    }

    public Student(int studentNumber, double grade) {
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    @Override
    public String toString() {
        return "Klas: " + klas + " Student nummer: " + studentNumber + " Cijfer: " + grade;
    }

    @Override
    public int compareTo(Student s1) {
        return Double.compare(this.grade, s1.getGrade());
    }
}
