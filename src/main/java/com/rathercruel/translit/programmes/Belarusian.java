package com.rathercruel.translit.programmes;

import static com.rathercruel.translit.programmes.Ukrainian.caseAlgorithm;
import static com.rathercruel.translit.programmes.Ukrainian.sjaToSia;
import static com.rathercruel.translit.programmes.Ukrainian.toSoftLetter;
import static com.rathercruel.translit.programmes.Ukrainian.vowels;
import java.util.HashMap;

/**
 *
 * @author rathercruel
 */

public class Belarusian extends Ukrainian {
    static HashMap<String, String> softLetters = new HashMap<String, String>();
    static HashMap<String, String> alphabet = new HashMap<String, String>();
    private static HashMap<String, String> belarusianVowels = new HashMap<String, String>();
    public Belarusian(String message) {
        alphabet.put("'", "");
        alphabet.put("а", "a");
        alphabet.put("б", "b");
        alphabet.put("в", "v");
        alphabet.put("г", "h");
        alphabet.put("ґ", "g");
        alphabet.put("д", "d");
        alphabet.put("е", "je");
        alphabet.put("э", "e");
        alphabet.put("ж", "ž");
        alphabet.put("з", "z");
        alphabet.put("ы", "y");
        alphabet.put("і", "i");
        alphabet.put("ё", "jo");
        alphabet.put("й", "j");
        alphabet.put("к", "k");
        alphabet.put("м", "m");
        alphabet.put("н", "n");
        alphabet.put("о", "o");
        alphabet.put("п", "p");
        alphabet.put("р", "r");
        alphabet.put("с", "s");
        alphabet.put("т", "t");
        alphabet.put("у", "u");
        alphabet.put("ў", "ŭ");
        alphabet.put("ф", "f");
        alphabet.put("х", "ch");
        alphabet.put("ц", "c");
        alphabet.put("ч", "č");
        alphabet.put("ш", "š");
        alphabet.put("ь", "");
        alphabet.put("ю", "ju");
        alphabet.put("я", "ja");

        softLetters.put("з", "ź");
        softLetters.put("н", "ń");
        softLetters.put("с", "ś");
        softLetters.put("ц", "ć");
        
        belarusianVowels.put("я", "a");
        belarusianVowels.put("е", "e");
        belarusianVowels.put("ю", "u");
        belarusianVowels.put("ё", "o");
        belarusianVowels.put("і", "i");

        char nextLetter = 0;
        boolean isNextLetterUpper = false;
        boolean isPreviousLetterConsonant = false;
        for(int index = 0; index < message.length(); index++) {
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

            latinLetter = sjaToSia(alphabet, latinLetter, isPreviousLetterConsonant);
            latinLetter = toSoftLetter(softLetters, latinLetter, loweredLetter, nextLetter);
            latinLetter = liFix(loweredLetter, nextLetter, latinLetter, message, belarusianVowels, alphabet, index);

            output = caseAlgorithm(
                    alphabet, index, currentLetter, latinLetter, loweredLetter,
                    nextLetter, isNextLetterUpper, output, message
            );
            isNextLetterUpper = false;
            isPreviousLetterConsonant = !(new String(vowels).contains(String.valueOf(loweredLetter))) && loweredLetter != ' ';
            if (currentLetter == '\'' || currentLetter == 'ь') isPreviousLetterConsonant = false;
            
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