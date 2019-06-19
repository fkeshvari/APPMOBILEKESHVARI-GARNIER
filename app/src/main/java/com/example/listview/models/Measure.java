package com.example.listview.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Measure {
    public static final int NOTYPE = 0;
    public static final int L = 1;
    public static final int DL = 2;
    public static final int CL = 3;
    public static final int ML = 4;
    public static final int G = 5;
    public static final int MG = 6;
    public static final int KG = 7;
    public static ArrayList<String> measures = new ArrayList<String>(Arrays.asList("L", "dL", "cL", "mL", "g", "mg", "kg"));

    public static int getInt(String measure) {
        switch (measure) {
            case "-":
                return 0;
            case "L":
                return 1;
            case "dL":
                return 2;
            case "cL":
                return 3;
            case "mL":
                return 4;
            case "g":
                return 5;
            case "mg":
                return 6;
            case "kg":
                return 7;
            default:
                return -1;
        }
    }

    public static String parseMeasureAPI(String s){
        for (String measure :measures) {
            if (s.contains(" "+measure)){
                return measure;
            }
        }
        return null;
    }
}
