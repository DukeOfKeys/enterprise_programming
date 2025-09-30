package LW3;

public class Caller {
    public static void main(String[] args) {
        //  String text = "Текст123 !!! 4567---abc***++++== Конец. \n мамлам vhrevjeruov ";
        // String result = StringProblems.findLongestNonLetterSubstring(text);
        // System.out.println("Исходный текст: " + text);
        // System.out.println("Первая максимальная подстрока без букв: \"" + result + "\"");


          String text2 = "Это простой тест для слов разной длины \nfhrf cjffvf gfjvhjf fec ervfdbbhcdfvb \n rfr vvfhj \n jkv fdlfkd ";
        int lengthToReplace = 3;
        String replacement = "XXX_YYY";

        String result2 = StringProblems.replaceWordsByLengthLetters(text2, lengthToReplace, replacement);
        System.out.println("Исходный текст: " + text2);
        System.out.println("Результат:      " + result2);
    }
}
