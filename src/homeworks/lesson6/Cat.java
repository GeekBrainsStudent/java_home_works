package homeworks.lesson6;

import java.util.Random;

public class Cat extends Animal {

    @Override
    protected double setRunLimit() {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        return randomDouble + 100 + (randomDouble * 200);
    }

    @Override
    protected double setSwimLimit() {
        return 0.0;
    }

    @Override
    protected double setJumpLimit() {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        return 2.0 * randomDouble + 1.0;
    }
}
