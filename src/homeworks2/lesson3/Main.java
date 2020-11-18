package homeworks2.lesson3;

import java.util.*;

public class Main {
    public static void main(String[] args) {

//        Task 1
        System.out.println("---- Task1 ----");
        String[] words = {"String", "Java", "Interface",
            "String", "Python", "C#", "Java", "String",
            "String", "Interface", "C#", "Interface", "C#"};

        printWords(words);

//        Task 2
        System.out.println("---- Task2 ----");
        PhoneBook book = new PhoneBook();
        book.add("Деникин", 123);
        book.add("Пугачев", 234);
        book.add("Горбачев", 345);
        book.add("Деникин", 456);
        book.add("Пугачев", 567);
        book.add("Деникин", 678);

        book.printPhone("Деникин");
    }

    static void printWords(String[] arr) {
        var map = createMap(arr);
        map.forEach((k,v) -> System.out.println(k + ": " + v));
    }

    static Map<String,Integer> createMap(String[] arr) {
        var map = new HashMap<String, Integer>(arr.length);
        for (String s : arr) {
            int value = map.getOrDefault(s, 1);
            if (map.put(s, value) != null)
                map.put(s, ++value);
        }
        return map;
    }
}
