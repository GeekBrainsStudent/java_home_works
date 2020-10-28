package homeworks.lesson6;

import java.util.Random;

public class Dog extends Animal {

    @Override
    protected double setRunLimit() {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        return randomDouble + 400 + (randomDouble * 200);
    }

    @Override
    protected double setSwimLimit() {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        return randomDouble + 8 + (randomDouble * 4);
    }

    @Override
    protected double setJumpLimit() {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        return randomDouble + 0.3 + (randomDouble * 0.4);
    }
}
