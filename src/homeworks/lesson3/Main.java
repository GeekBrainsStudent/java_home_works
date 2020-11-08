package homeworks.lesson3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {

    static String[] words = {"apple", "orange", "lemon", "banana", "apricot",
            "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
            "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
            "pear", "pepper", "pineapple", "pumpkin", "potato"};

    static BufferedReader br;
    static final int hintLength = 15;           // длина выводимой подсказки
    static char[] charsCorrectAnswer;           // символы загаданного слова
    static char[] charsUserAnswer;              // символы введенного пользователем слова
    static char[] charsCompareAnswers = new char[hintLength];   // символы подсказки

//    возвращает случайное слово из заданного массива
    static String getRandomElement(String[] arr) {
        Random random = new Random();
        int randomIndex = random.nextInt(arr.length - 1);
        return arr[randomIndex];
    }

//    Начальное сообщение
    static void printStartMessage() {
        System.out.println("Загадано слово из следующих: ");
        for(int i = 0; i < words.length; i++) {
            System.out.print(words[i] + ", ");
            if(i != 0 && (i % 5) == 0)
                System.out.println();
        }
        System.out.print("\n\nВведите Ваш вариант: ");
    }

//    Возвращает символ для массива подсказки
    static char fillCompareChars(int i) {
        return (charsUserAnswer[i] == charsCorrectAnswer[i]) ?
                charsCorrectAnswer[i] : '#';

    }

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        charsCorrectAnswer = getRandomElement(words).toCharArray();
        printStartMessage();
        while(true) {
            charsUserAnswer = br.readLine().toCharArray();
            System.out.println();

//            Если дан правильный ответ
            if(Arrays.equals(charsUserAnswer, charsCorrectAnswer)) {
                System.out.println("Поздравляю, вы угадали!");
                break;
            }

            boolean isGuessedSymbol = false;        // Флаг, юзер угадал хотя бы один символ

            int minCharsLength = Math.min(charsCorrectAnswer.length, charsUserAnswer.length);
            for(int i = 0; i < hintLength; i++) {
                charsCompareAnswers[i] = (i < minCharsLength) ?
                        fillCompareChars(i) : '#';
                if(charsCompareAnswers[i] != '#')
                    isGuessedSymbol = true;
            }

            String message = (!isGuessedSymbol) ?
                    "Не верно." : new String(charsCompareAnswers);
            System.out.print(message + "\nПопробуйте еще раз: ");
        }
    }
}