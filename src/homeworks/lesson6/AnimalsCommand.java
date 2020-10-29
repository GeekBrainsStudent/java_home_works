package homeworks.lesson6;

import java.util.Random;

class AnimalsCommand {

    int numbAnimals = 5;
    Random random = new Random();

    AnimalsCommand(int numbAnimals) {
        this.numbAnimals = numbAnimals;
    }

    void goGoGo() {
        for (int i = 0; i < numbAnimals; i++) {

            Animal animal = (random.nextInt() % 2 == 0) ? new Dog() : new Cat();
            animal.setLimits();
            printAnimalInfo(animal, i + 1);

            animalRun(animal, getRunLength(), i + 1);
            animalSwim(animal, getSwimLength(), i + 1);
            animalJump(animal, getJumpHeight(), i + 1);
        }
    }

    private void printAnimalInfo(Animal animal, int number) {
        System.out.printf("\n\nAnimal № %d - это %s. ", number, animal.getClass().getSimpleName());
        System.out.printf("\nМаксимальная дистанция бега = %.2f метров", animal.runLimit);
        System.out.printf("\nМаксимальная дистанция плавания = %.2f метров", animal.swimLimit);
        System.out.printf("\nМаксимальная высота прыжка = %.2f метров", animal.jumpLimit);
    }

    private void animalRun(Animal animal, double length, int numberAnimal) {
        String message = animal.run(length) ? "Пробежала" : "Не может пробежать";
        printMessage(animal, length, numberAnimal, message);
    }

    private double getRunLength() {
        double randomDouble = random.nextDouble();
        return randomDouble + 100 + (600 * randomDouble); // дистанция от 100 до ~640.9 метров
    }

    private void animalSwim(Animal animal, double length, int numberAnimal) {
        String message = animal.swim(length) ? "Проплыла" : "Не может проплыть";
        printMessage(animal, length, numberAnimal, message);
    }

    private double getSwimLength() {
        double randomDouble = random.nextDouble();
        return randomDouble + 8 + (5 * randomDouble);  // дистанция от 8 до ~13.4 метров
    }

    private void animalJump(Animal animal, double height, int numberAnimal) {
        String message = animal.jump(height) ? "Прыгнула" : "Не может прыгнуть";
        printMessage(animal, height, numberAnimal, message);
    }

    private double getJumpHeight() {
        double randomDouble = random.nextDouble();
        return randomDouble + 0.3 + (3 * randomDouble);  // высота от 0.3 до ~3.9 метров
    }

    private void printMessage(Animal animal, double length, int numberAnimal, String message) {
        System.out.printf("\nAnimal № %d %s %.2f метров", numberAnimal, message, length);
    }
}
