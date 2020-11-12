package homeworks2.lesson1.barriers;

import java.util.Random;

public class Wall extends Barrier{
    public Wall() {
        Random random = new Random();
        barrier = random.nextDouble() * 0.25 + 0.25; // высота от 0.25 до 0.5 метров
    }

    public double getBarrier() {
        return barrier;
    }

    @Override
    public void printInfo() {
        System.out.printf("\nСтена с высотой: %.2f метров",barrier);
    }
}
