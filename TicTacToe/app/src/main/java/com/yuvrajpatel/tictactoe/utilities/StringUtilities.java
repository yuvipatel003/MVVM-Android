package com.yuvrajpatel.tictactoe.utilities;

import android.text.TextUtils;

public class StringUtilities {

    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers)
            sNumbers.append(number);
        return sNumbers.toString();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public static boolean isValidName(String name){

        if(TextUtils.isEmpty(name) || name == null){
            return false;
        }
        return true;
    }

}
