package com.rathercruel.translit.programmes;

import java.util.HashMap;
/**
 *
 * @author rathercruel
 */

public class PsevdoJireckivka extends Ukrainian {
    private static HashMap<String, String> softLetters = new HashMap<String, String>();
    private static HashMap<String, String> alphabet = new HashMap<String, String>();
    public PsevdoJireckivka(String message) {
        alphabet.put("'", "");
        alphabet.put("а", "a");
        alphabet.put("б", "b");
        alphabet.put("в", "v");
        alphabet.put("г", "h");
        alphabet.put("ґ", "g");
        alphabet.put("д", "d");
        alphabet.put("е", "e");
        alphabet.put("є", "je");
        alphabet.put("ж", "ž");
        alphabet.put("з", "z");
        alphabet.put("и", "y");
        alphabet.put("і", "i");
        alphabet.put("ї", "ji");
        alphabet.put("й", "j");
        alphabet.put("к", "k");
        alphabet.put("л", "l");
        alphabet.put("м", "m");
        alphabet.put("н", "n");
        alphabet.put("о", "o");
        alphabet.put("п", "p");
        alphabet.put("р", "r");
        alphabet.put("с", "s");
        alphabet.put("т", "t");
        alphabet.put("у", "u");
        alphabet.put("ф", "f");
        alphabet.put("х", "ch");
        alphabet.put("ц", "c");
        alphabet.put("ч", "č");
        alphabet.put("ш", "š");
        alphabet.put("щ", "šč");
        alphabet.put("ь", "");
        alphabet.put("ю", "ju");
        alphabet.put("я", "ja");

        softLetters.put("д", "ď");
        softLetters.put("л", "ľ");
        softLetters.put("т", "ť");
        softLetters.put("з", "ź");
        softLetters.put("н", "ń");
        softLetters.put("р", "ŕ");
        softLetters.put("с", "ś");
        softLetters.put("ц", "ć");

        char nextLetter = 0;
        boolean isNextLetterUpper = false;
        boolean isPreviousLetterConsonant = false;
        for(int index = 0; index < message.length(); index++) {
            char currentLetter = message.charAt(index);
            char loweredLetter = Character.toLowerCase(currentLetter);
            String latinLetter = alphabet.get(String.valueOf(loweredLetter));

            // Creates nextLetter variable (but it's assigned outside the loop)
            if(index != message.length() - 1) {
                nextLetter = message.charAt(index + 1);
                if(Character.isUpperCase(nextLetter)) {
                    isNextLetterUpper = true;
                }
                nextLetter = Character.toLowerCase(nextLetter);
            }

            // Changes "sja" to "sia"
            latinLetter = sjaToSia(alphabet, latinLetter, isPreviousLetterConsonant, loweredLetter);

            // Makes letters soft
            latinLetter = toSoftLetterWithI(softLetters, index, message, latinLetter, loweredLetter, nextLetter);

            output = caseAlgorithm(
                    alphabet, index, currentLetter, latinLetter, loweredLetter,
                    nextLetter, isNextLetterUpper, output, message
            );
            isNextLetterUpper = false;
            isPreviousLetterConsonant = !(new String(vowels).contains(String.valueOf(loweredLetter))) && loweredLetter != ' ';
            if (loweredLetter == '\'' || loweredLetter == 'ь') isPreviousLetterConsonant = false;
            else if (!alphabet.containsKey(String.valueOf(loweredLetter))) isPreviousLetterConsonant = false;
        }
    }
    public String getOutput() {
        return output;
    }
}
