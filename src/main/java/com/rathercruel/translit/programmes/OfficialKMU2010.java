package com.rathercruel.translit.programmes;

import static com.rathercruel.translit.programmes.Ukrainian.caseAlgorithm;
import static com.rathercruel.translit.programmes.Ukrainian.sjaToSia;
import static com.rathercruel.translit.programmes.Ukrainian.toSoftLetterWithI;
import static com.rathercruel.translit.programmes.Ukrainian.vowels;
import java.util.HashMap;
/**
 *
 * @author rathercruel
 */

public class OfficialKMU2010 extends Ukrainian {
    private static HashMap<String, String> alphabet = new HashMap<String, String>();
    public OfficialKMU2010(String message) {
        alphabet.put("'", "");
        alphabet.put("а", "a");
        alphabet.put("б", "b");
        alphabet.put("в", "v");
        alphabet.put("г", "h");
        alphabet.put("ґ", "g");
        alphabet.put("д", "d");
        alphabet.put("е", "e");
        alphabet.put("є", "ye");
        alphabet.put("ж", "zh");
        alphabet.put("з", "z");
        alphabet.put("и", "y");
        alphabet.put("і", "i");
        alphabet.put("ї", "yi");
        alphabet.put("й", "y");
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
        alphabet.put("х", "kh");
        alphabet.put("ц", "ts");
        alphabet.put("ч", "ch");
        alphabet.put("ш", "sh");
        alphabet.put("щ", "shch");
        alphabet.put("ь", "");
        alphabet.put("ю", "yu");
        alphabet.put("я", "ya");

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

            // Changes "sya" to "sia"
            if (alphabet.containsValue(latinLetter) && latinLetter.length() == 2 && latinLetter.charAt(0) == 'y' && loweredLetter != 'ї') {
                if (isPreviousLetterConsonant) latinLetter = "i" + latinLetter.charAt(1);
            }

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
