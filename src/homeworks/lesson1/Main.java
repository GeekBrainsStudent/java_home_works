package homeworks.lesson1;

public class Main {

    public static void main(String[] args) {
//      инициализация переменных
        byte b = 127;
        short s = 32_767;
        int i = 0;
        char c = 'A';
        double d = 3.14;
        float f = 0.045f;
        boolean bool = true;
        String str = "ABC";

//        проверка функций
        System.out.println(calcExpression(2,1,2,0));
        System.out.println(isAmountInRange(15,5));
        printSign(-100);
        System.out.println(isNegative(100));
        sayHello("Вивиен");
        printIsLeap(300);
    }

//     метод вычисляющий выражение a * (b + (c / d))
//     В случае если d == 0, значение d игнорируется и не влияет на результат
    static double calcExpression(int a, int b, int c, int d) {
        if(d == 0)
            d = 1;
        return a * (b + (c/d));
    }

//    метод, принимающий на вход два числа,
//    и проверяющий что их сумма лежит в пределах от 10 до 20(включительно)
    static boolean isAmountInRange(int x, int y) {
        int sum = x + y;
        return (sum >= 10) && (sum <= 20);
    }

//    метод, которому в качестве параметра передается целое число,
//    метод должен напечатать в консоль положительное ли число передали, или отрицательное
    static void printSign(int i) {
        if(i < 0)
            System.out.println("Отрицательное");
        else
            System.out.println("Положительное");
    }

//    метод, которому в качестве параметра передается целое число,
//    метод должен вернуть true, если число отрицательное
    static boolean isNegative(int i) {
        return i < 0;
    }

//    метод, которому в качестве параметра передается строка, обозначающая имя,
//    метод должен вывести в консоль сообщение «Привет, указанное_имя!»
    static void sayHello(String name) {
        System.out.println("Привет, " + name + "!");
    }

//    метод, который определяет является ли год високосным,
//    и выводит сообщение в консоль.
    static void printIsLeap(int year) {
        String result = "Не високосный";
        if(((year % 4) == 0 && (year % 100) != 0) || ((year % 400) == 0))
                result = "Високосный";
        System.out.println(result);
    }
}
