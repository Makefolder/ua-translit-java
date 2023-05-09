package com.rathercruel.translit.programmes;

import java.util.HashMap;

/**
 *
 * @author rathercruel
 */

public class Ukrainian {
    String output = "";
    static char[] vowels = new char[] {'а', 'е', 'є', 'і', 'ї', 'о', 'и', 'ю', 'я'};

    // Changes "sja" to "sia"
    public static String sjaToSia(
            HashMap<String, String> alphabet, String latinLetter, boolean isPreviousLetterConsonant
    ) {
        if (alphabet.containsValue(latinLetter) && latinLetter.length() == 2 && latinLetter.charAt(0) == 'j'){
            if (isPreviousLetterConsonant) latinLetter = "i" + latinLetter.charAt(1);
        }
        return latinLetter;
    }

    // Makes letters soft
    public static String toSoftLetterWithI(
            HashMap<String, String> softLetters, int index, String message,
            String latinLetter, char loweredLetter, char nextLetter
    ) {
        if (softLetters.containsKey(String.valueOf(loweredLetter)) && nextLetter == 'ь') {
            String secondLatinLetter = softLetters.get(String.valueOf(loweredLetter));
            if (index + 2 < message.length()) {
                if (Character.toLowerCase(message.charAt(index + 2)) == 'о') secondLatinLetter = latinLetter;
            }
            latinLetter = secondLatinLetter;
        }
        if (loweredLetter == 'ь' && nextLetter == 'о') latinLetter = "i";
        return latinLetter;
    }

    public static String toSoftLetter(
            HashMap<String, String> softLetters,
            String latinLetter, char loweredLetter, char nextLetter
    ) {
        if (softLetters.containsKey(String.valueOf(loweredLetter)) && nextLetter == 'ь') {
            latinLetter = softLetters.get(String.valueOf(loweredLetter));;
        }
        return latinLetter;
    }

    // Algorithm that changes Upper/Lower case of the letters
    public static String caseAlgorithm(
            HashMap<String, String> alphabet, int index, char currentLetter,
            String latinLetter, char loweredLetter, char nextLetter,
            boolean isNextLetterUpper, String output, String message
    ) {
        if (alphabet.containsKey(String.valueOf(loweredLetter))) {
            if (latinLetter.length() > 1) {
                String firstCharacter = String.valueOf(latinLetter.charAt(0));
                String secondCharacter = String.valueOf(latinLetter.charAt(1));
                String latinTitle = firstCharacter.toUpperCase() + secondCharacter.toLowerCase();

                if (Character.isUpperCase(currentLetter) && isNextLetterUpper) latinLetter = latinLetter.toUpperCase();
                if (Character.isUpperCase(currentLetter) && !(isNextLetterUpper)) latinLetter = latinTitle;
                if (index > 1) {
                    char previousLetter = message.charAt(index - 1);
                    if (
                            Character.isUpperCase(previousLetter) &&
                                    Character.isUpperCase(currentLetter) &&
                                    !(alphabet.containsKey(nextLetter))
                    ) {
                        latinLetter = latinLetter.toUpperCase();
                    }
                }
            } else {
                if (Character.isUpperCase(currentLetter)) latinLetter = latinLetter.toUpperCase();
                else latinLetter = latinLetter.toLowerCase();
            }
            output += latinLetter;
        } else output += currentLetter;
        return output;
    }
}
