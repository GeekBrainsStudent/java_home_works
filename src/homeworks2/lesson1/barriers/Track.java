package homeworks2.lesson1.barriers;

import java.util.Random;

public class Track extends Barrier {

    public Track() {
        Random random = new Random();
        barrier = random.nextDouble() * 50.0 + 50.0; // дистанция от 50 до ~100 метров
    }

    public double getBarrier() {
        return barrier;
    }
}
