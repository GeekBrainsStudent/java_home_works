package homeworks.lesson6;

import java.util.Random;

abstract class Animal {

    Random random;

    Animal() {
        random = new Random();
        this.runLimit = -1.0;
        this.swimLimit = -1.0;
        this.jumpLimit = -1.0;
    }

    protected double runLimit;
    protected double swimLimit;
    protected double jumpLimit;

    boolean run(double length) {
        if(runLimit < 0)
            System.out.println("Перед использованием объекта, вызовите метод setLimits");
        return runLimit >= length;
    }

    boolean swim(double length) {
        if(runLimit < 0)
            System.out.println("Перед использованием объекта, вызовите метод setLimits");
        return swimLimit >= length;
    }

    boolean jump(double height) {
        if(runLimit < 0)
            System.out.println("Перед использованием объекта, вызовите метод setLimits");
        return jumpLimit >= height;
    }

    protected abstract double setRunLimit();
    protected abstract double setSwimLimit();
    protected abstract double setJumpLimit();

    protected void setLimits() {
        runLimit = setRunLimit();
        swimLimit = setSwimLimit();
        jumpLimit = setJumpLimit();
    }
}
