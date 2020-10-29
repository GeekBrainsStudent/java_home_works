package homeworks.lesson6;

public class Cat extends Animal {

    @Override
    protected double setRunLimit() {
        double randomDouble = random.nextDouble();
        return randomDouble + 100 + (randomDouble * 200); // бежит от 100 до ~280.9 метров
    }

    @Override
    protected double setSwimLimit() {
        return 0.0;
    } // не плывавет

    @Override
    protected double setJumpLimit() {
        double randomDouble = random.nextDouble();
        return 2.0 * randomDouble + 1.0;            // прыгает от 1 до ~2.8 метров
    }
}
