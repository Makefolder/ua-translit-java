package com.rathercruel.translit.programmes;

import static com.rathercruel.translit.programmes.Ukrainian.caseAlgorithm;
import java.util.HashMap;

/**
 *
 * @author rathercruel
 */

public class TkpnCombo extends Ukrainian {
private static HashMap<String, String> alphabet = new HashMap<String, String>();
    public TkpnCombo(String message) {
        alphabet.put("'", "'");
        alphabet.put("а", "a");
        alphabet.put("б", "b");
        alphabet.put("в", "v");
        alphabet.put("г", "ğ");
        alphabet.put("ґ", "g");
        alphabet.put("д", "d");
        alphabet.put("е", "e");
        alphabet.put("є", "je");
        alphabet.put("ж", "ž");
        alphabet.put("з", "z");
        alphabet.put("и", "y");
        alphabet.put("і", "i");
        alphabet.put("ї", "ï");
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
        alphabet.put("х", "x");
        alphabet.put("ц", "c");
        alphabet.put("ч", "č");
        alphabet.put("ш", "š");
        alphabet.put("щ", "ŝ");
        alphabet.put("ь", "j");
        alphabet.put("ю", "ju");
        alphabet.put("я", "ja");
        
        char nextLetter = 0;
        boolean isNextLetterUpper = false;
        for(int index = 0; index < message.length(); index++) {
            char currentLetter = message.charAt(index);
            char loweredLetter = Character.toLowerCase(currentLetter);
            String latinLetter = alphabet.get(String.valueOf(loweredLetter));

            if (index != message.length() - 1) {
                nextLetter = message.charAt(index + 1);
                if (Character.isUpperCase(nextLetter)) {
                    isNextLetterUpper = true;
                }
                nextLetter = Character.toLowerCase(nextLetter);
            }
            
            output = caseAlgorithm(
                    alphabet, index, currentLetter, latinLetter, loweredLetter,
                    nextLetter, isNextLetterUpper, output, message
            );
            isNextLetterUpper = false;
        }
    }
    
    public String getOutput() {
        return output;
    }
}