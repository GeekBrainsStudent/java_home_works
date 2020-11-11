package homeworks2.lesson1.competitors;

import homeworks2.lesson1.interfaces.Competitor;

import java.util.Random;

public class Man implements Competitor {
    private final String name;
    private final double speed;
    private final double jumpHeight;
    private double time;                // время преодоления трэка

    public Man(String name) {
        this.name = name;
        Random random = new Random();
        speed = random.nextDouble() * 3.0 + 5.0; // скорость от 5 до ~8 м/с
        jumpHeight = random.nextDouble() * 0.5 + 0.3; // высота прыжка от 0.3 до ~0.8 метров
        time = 0.0;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean jump(double height) {
        return jumpHeight >= height;
    }

    @Override
    public double run(double distance) {
        return distance / speed;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }
}
