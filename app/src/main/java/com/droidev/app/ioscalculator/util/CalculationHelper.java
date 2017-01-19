package com.droidev.app.ioscalculator.util;

import java.text.DecimalFormat;

/**
 * Created by Shajju on 19/01/2017.
 */

public class CalculationHelper {

    public static String sumTwoNumbers(double first, double second) {
        double result = first + second;
        return roundOffIfZeroComes(result);
    }

    public static String substractTwoNumbers(double first, double second) {
        double result = first - second;
        return roundOffIfZeroComes(result);
    }

    public static String multiplyTwoNumbers(double first, double second) {
        double result = first * second;
        return roundOffIfZeroComes(result);
    }

    public static String divideTwoNumbers(double first, double second) {
        double result = first / second;
        return roundOffIfZeroComes(result);
    }

    public static String percentageTwoNumbers(double first, double second) {
        double result = first / second * 100;
        return roundOffIfZeroComes(result);
    }

    private static String roundOffIfZeroComes(double number) {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalSeparatorAlwaysShown(false);
        return format.format(number);
    }
}
