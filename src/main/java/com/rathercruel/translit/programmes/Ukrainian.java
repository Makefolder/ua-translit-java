package com.rathercruel.translit.programmes;

import java.util.HashMap;

/**
 *
 * @author rathercruel
 */

public class Ukrainian {
    public String output = "";
    public static boolean isSofted = false;
    public static boolean isChanged = false;
    static char[] vowels = new char[] {'а', 'е', 'є', 'і', 'ї', 'о', 'и', 'ю', 'я'};

    // Changes "sja" to "sia"
    public static String sjaToSia(
            HashMap<String, String> alphabet, String latinLetter, boolean isPreviousLetterConsonant, char loweredLetter
    ) {
        // TODO: Change isPreviousLetterConsonant to false in liFix method or in the file that uses the method;
        if (alphabet.containsValue(latinLetter) && latinLetter.length() == 2 && latinLetter.charAt(0) == 'j' && loweredLetter != 'ї') {
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
            latinLetter = softLetters.get(String.valueOf(loweredLetter));
        }
        return latinLetter;
    }
    
    public static String liFix(
            char loweredLetter, char nextLetter,
            String latinLetter, String message, HashMap<String, String> vowels,
            HashMap<String, String> alphabet,int index
    ) {
        if ("ł".equals(latinLetter) && index != message.length() - 1) {
            String stringNextLetter = "" + nextLetter;
            String nextLatin = alphabet.get(String.valueOf(nextLetter));
            String secondLatinLetter = vowels.get(String.valueOf(nextLetter));
            if (vowels.containsKey(stringNextLetter)) {
                latinLetter = "l" + secondLatinLetter;
            }
            else {
                if (nextLatin != null) latinLetter = "ł" + nextLatin;
                else latinLetter = "ł" + nextLetter;
            }
            isChanged = true;
        }
        return latinLetter;
    }
    
    public static String softBeforeVowels(
            char loweredLetter, char nextLetter,
            String latinLetter, String message, HashMap<String, String> vowels,
            HashMap<String, String> alphabet, HashMap<String, String> softLetters,int index
    ) {
        if (softLetters.containsKey(String.valueOf(loweredLetter)) && index != message.length() - 1) {
            String stringNextLetter = "" + nextLetter;
            String nextLatin = alphabet.get(String.valueOf(nextLetter));
            String secondLatinLetter = vowels.get(String.valueOf(nextLetter));
            if (vowels.containsKey(stringNextLetter)) {
                latinLetter = softLetters.get(String.valueOf(loweredLetter)) + secondLatinLetter;
            }
            else {
                if (nextLatin != null) latinLetter = latinLetter + nextLatin;
                else latinLetter = latinLetter + nextLetter;
            }
            isSofted = true;
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
                
                if (Character.isUpperCase(currentLetter)) {
                    if (isNextLetterUpper) latinLetter = latinLetter.toUpperCase();
                    if (!(isNextLetterUpper)) latinLetter = latinTitle;
                }
                
                // Could use try {...} catch (Exception e) {}
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

