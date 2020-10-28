package homeworks.lesson6;

abstract class Animal {

    Animal() {
        this.runLimit = -1.0;
        this.swimLimit = -1.0;
        this.jumpLimit = -1.0;
    }

    protected double runLimit;
    protected double swimLimit;
    protected double jumpLimit;

    protected boolean run(double length) {
        if(runLimit < 0)
            System.out.println("Перед использованием объекта, вызовите метод setLimits");
        return runLimit >= length;
    }

    protected boolean swim(double length) {
        if(runLimit < 0)
            System.out.println("Перед использованием объекта, вызовите метод setLimits");
        return swimLimit >= length;
    }

    protected boolean jump(double height) {
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
