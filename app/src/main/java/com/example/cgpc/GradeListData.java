package com.example.cgpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GradeListData {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> Grades = new HashMap<String, List<String>>();

        List<String> S1 = new ArrayList<>();
        S1.add("CALCULUS");
        S1.add("ENGINEERING CHEMISTRY");
        S1.add("ENGINEERING GRAPHICS");
        S1.add("INTRODUCTION TO COMPUTING AND PROBLEM SOLVING");
        S1.add("INTRODUCTION TO SUSTAINABLE ENGINEERING");
        S1.add("BASICS OF ELECTRICAL ENGINEERING");
        S1.add("ENGINEERING CHEMISTRY LAB");
        S1.add("ELECTRICAL ENGINEERING WORKSHOP");
        S1.add("COMPUTER SCIENCE WORKSHOP");

        List<String> S2 = new ArrayList<>();
        S2.add("DIFFERENTIAL EQUATIONS");
        S2.add("ENGINEERING PHYSICS");
        S2.add("ENGINEERING MECHANICS");
        S2.add("DESIGN & ENGINEERING");
        S2.add("ENGINEERING PHYSICS LAB");
        S2.add("BASICS OF ELECTRONICS ENGINEERING");
        S2.add("ELECTRONICS ENGINEERING WORKSHOP");
        S2.add("Computer Programming Lab");
        S2.add("BASICS OF COMPUTER PROGRAMMING");


        Grades.put("Semster 1", S1);
        Grades.put("Semster 2", S2);


        return Grades;
    }
}
