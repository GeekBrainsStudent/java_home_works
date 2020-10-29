package homeworks.lesson6;

public class Main {
    public static void main(String[] args) {
        int numbAnimals = 5;
        AnimalsCommand command = new AnimalsCommand(numbAnimals);
        command.goGoGo();
    }
}
