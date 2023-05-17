package com.rathercruel.translit.programmes;

import java.util.HashMap;

/**
 *
 * @author rathercruel
 */

public class Abecadlo extends Ukrainian {
    private static HashMap<String, String> softLetters = new HashMap<String, String>();
    private static HashMap<String, String> alphabet = new HashMap<String, String>();
    private static HashMap<String, String> ukrainianVowels = new HashMap<String, String>();
    public Abecadlo(String message) {
        alphabet.put("'", "");
        alphabet.put("а", "a");
        alphabet.put("б", "b");
        alphabet.put("в", "w");
        alphabet.put("г", "h");
        alphabet.put("ґ", "g");
        alphabet.put("д", "d");
        alphabet.put("е", "e");
        alphabet.put("є", "je");
        alphabet.put("ж", "ż");
        alphabet.put("з", "z");
        alphabet.put("и", "y");
        alphabet.put("і", "i");
        alphabet.put("ї", "ji");
        alphabet.put("й", "j");
        alphabet.put("к", "k");
        alphabet.put("л", "ł");
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
        alphabet.put("ч", "cz");
        alphabet.put("ш", "sz");
        alphabet.put("щ", "szcz");
        alphabet.put("ь", "");
        alphabet.put("ю", "ju");
        alphabet.put("я", "ja");

        softLetters.put("д", "ď");
        softLetters.put("л", "l");
        softLetters.put("т", "ť");
        softLetters.put("з", "ź");
        softLetters.put("н", "ń");
        softLetters.put("р", "ŕ");
        softLetters.put("с", "ś");
        softLetters.put("ц", "ć");
        
        ukrainianVowels.put("я", "a");
        ukrainianVowels.put("є", "e");
        ukrainianVowels.put("ю", "u");
        ukrainianVowels.put("і", "i");
        
        char nextLetter = 0;
        boolean isNextLetterUpper = false;
        boolean isPreviousLetterConsonant = false;
        for (int index = 0; index < message.length(); index++) {
            char currentLetter = message.charAt(index);
            char loweredLetter = Character.toLowerCase(currentLetter);
            String latinLetter = alphabet.get(String.valueOf(loweredLetter));

            if(index != message.length() - 1) {
                nextLetter = message.charAt(index + 1);
                if(Character.isUpperCase(nextLetter)) {
                    isNextLetterUpper = true;
                }
                nextLetter = Character.toLowerCase(nextLetter);
            }

            latinLetter = sjaToSia(alphabet, latinLetter, isPreviousLetterConsonant, loweredLetter);
            latinLetter = toSoftLetter(softLetters, latinLetter, loweredLetter, nextLetter);
            
            latinLetter = liFix(loweredLetter, nextLetter, latinLetter, message, ukrainianVowels, alphabet, index);

            output = caseAlgorithm(
                    alphabet, index, currentLetter, latinLetter, loweredLetter,
                    nextLetter, isNextLetterUpper, output, message
            );
            isNextLetterUpper = false;
            isPreviousLetterConsonant = !(new String(vowels).contains(String.valueOf(loweredLetter))) && loweredLetter != ' ';
            if (loweredLetter == '\'' || loweredLetter == 'ь') isPreviousLetterConsonant = false;
            else if (!alphabet.containsKey(String.valueOf(loweredLetter))) isPreviousLetterConsonant = false;
            
            if (Ukrainian.isChanged) {
                index++;
                Ukrainian.isChanged = false;
                isPreviousLetterConsonant = false;
            }
        }
    }
    public String getOutput() {
        return output;
    }
}
