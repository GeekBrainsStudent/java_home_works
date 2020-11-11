package homeworks2.lesson1.competitors;

import homeworks2.lesson1.interfaces.Competitor;

import java.util.Random;

public class Cat implements Competitor {
    private final String name;          // имя
    private final double speed;         // скорость в м/с
    private final double jumpHeight;    // высота прыжка в м
    private double time;                // время преодоления трэка

    public Cat(String name) {
        this.name = name;
        Random random = new Random();
        speed = random.nextDouble() * 2.0 + 1.0; // скорость от 1 до ~3 м/с
        jumpHeight = random.nextDouble() + 1.0; // высота прыжка от 1 до ~2 метров
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