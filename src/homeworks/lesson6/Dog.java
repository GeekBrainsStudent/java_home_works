package homeworks.lesson6;

public class Dog extends Animal {

    @Override
    protected double setRunLimit() {
        double randomDouble = random.nextDouble();
        return randomDouble + 400 + (randomDouble * 200); // бежит от 400 до ~580.9 метров
    }

    @Override
    protected double setSwimLimit() {
        double randomDouble = random.nextDouble();
        return randomDouble + 8 + (randomDouble * 4);   // плывет от 8 до ~12.5 метров
    }

    @Override
    protected double setJumpLimit() {
        double randomDouble = random.nextDouble();
        return randomDouble + 0.3 + (randomDouble * 0.4); // прыгает от 0.3 до ~1.56 метров
    }
}
