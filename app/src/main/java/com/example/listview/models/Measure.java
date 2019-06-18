package com.example.listview.models;

public class Measure {
    public static final int NOTYPE = 0;
    public static final int L = 1;
    public static final int DL = 2;
    public static final int CL = 3;
    public static final int ML = 4;
    public static final int G = 5;
    public static final int MG = 6;
    public static final int KG = 7;

    public static String getString(int measure) {
        switch (measure) {
            case NOTYPE:
                return "-";
            case L:
                return "L";
            case DL:
                return "dL";
            case CL:
                return "cL";
            case ML:
                return "mL";
            case G:
                return "g";
            case MG:
                return "mg";
            case KG:
                return "kg";
            default:
                return "";
        }
    }
}
