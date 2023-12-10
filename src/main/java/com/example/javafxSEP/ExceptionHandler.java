package com.example.javafxSEP;

    public class ExceptionHandler { // This class handles the exception logic for all the 4 add-project models
        // specifically it handles the logic for integer and double value exceptions

        public static int parseInt(String value) throws NumberFormatException { // Gives an exception if there is a string value, instead of an integer
            return Integer.parseInt(value);
        }

        public static double parseDouble(String value) throws NumberFormatException { // Gives an exception if there is a string value, instead of a double
            return Double.parseDouble(value);
        }
    }