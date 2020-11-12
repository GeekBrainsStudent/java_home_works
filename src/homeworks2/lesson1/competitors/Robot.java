package homeworks2.lesson1.competitors;

import homeworks2.lesson1.interfaces.Competitor;

import java.util.Random;

public class Robot implements Competitor {

    private final String name;          // имя
    private final double speed;         // скорость
    private final double jumpHeight;    // высота прыжка
    private double time;                // время преодоления трэка

    public Robot(String name) {
        this.name = name;
        Random random = new Random();
        speed = random.nextDouble() * 8.5 + 0.5; // скорость от 0.5 до ~9 м/с
        jumpHeight = random.nextDouble() * 2.8 + 0.2; // высота прыжка от 0.2 до ~3 метров
        time = 0.0;
    }

    @Override
    public void printInfo() {
        System.out.printf("\nRobot: %s, скорость: %.2f м/с, высота прыжка: %.2f метров", name,speed,jumpHeight);
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

    public String getName() {
        return name;
    }
}

