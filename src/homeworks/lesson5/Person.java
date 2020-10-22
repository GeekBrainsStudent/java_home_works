package homeworks.lesson5;

public class Person {

    protected String name;
    protected String surname;
    protected String patronymic;
    protected String position;
    protected String email;
    protected String phone;
    protected double salary;
    protected int age;

    public Person(String name,
                  String surname,
                  String patronymic,
                  String position,
                  String email,
                  String phone,
                  double salary,
                  int age) {

        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public Person(String name,
                  String surname,
                  String patronymic,
                  String position) {

        this(name,
                surname,
                patronymic,
                position,
                "undefined",
                "undefined",
                0.0,
                0);
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Сотрудник{" +
                "Имя='" + name + '\'' +
                ", Фамилия='" + surname + '\'' +
                ", Отчество='" + patronymic + '\'' +
                ", Должность='" + position + '\'' +
                ", Почта='" + email + '\'' +
                ", Телефон='" + phone + '\'' +
                ", Зарплата=" + salary +
                ", Возраст=" + age +
                '}';
    }
}
