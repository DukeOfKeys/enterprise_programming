package LW3;

import java.util.regex.Matcher;

public class StringProblems {
    /*4.	В тексте слова заданной длины заменить указанной подстрокой,
             длина которой может не совпадать с длиной слова. */
     public static String replaceWordsByLengthLetters(String text, int wordLength, String replacement) {
        String regex = "\\b\\p{L}{" + wordLength + "}\\b"; 
        return text.replaceAll(regex, Matcher.quoteReplacement(replacement));
    }
    /*
     * Находит первую подстроку максимальной длины, не содержащую букв
     */
        public static String findLongestNonLetterSubstring(String text) {
        String regex = "[^\\p{L}]+";

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(text);

        String longest = "";
        while (matcher.find()) {  
            String current = matcher.group();
            if (current.length() > longest.length()) {
                longest = current;
            }
        }
        return longest;
    }
}
