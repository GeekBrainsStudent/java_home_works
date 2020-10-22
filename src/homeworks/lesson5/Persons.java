package homeworks.lesson5;

public class Persons {

    public static void main(String[] args) {

        Person[] persons = new Person[5];

        persons[0] = new Person("Иван", "Иванов", "Иванович",
                "Начальник", "ivan@mail.ru", "+79065437854",
                250_000.00, 55);
        persons[1] = new Person("Мария", "Петрова", "Николаевна",
                "Главный бухгалтер", "maria@mail.ru", "+79064327564",
                150_000.00, 50);
        persons[2] = new Person("Иннокентий", "Гоголев", "Ефимович",
                "Инженер-программист", "kesha@mail.ru", "+7906865342",
                150_000.00, 41);
        persons[3] = new Person("Юлия", "Николаева", "Петровна",
                "Делопроизводитель", "julia@mail.ru", "+79064763820",
                50_000.00, 25);
        persons[4] = new Person("Николай", "Митрофанов", "Егорович",
                "Специалист", "nik@mail.ru", "+79063459854",
                50_000.00, 27);

        for(Person person : persons) {
            if(person.age > 40)
                person.print();
        }
    }


}
