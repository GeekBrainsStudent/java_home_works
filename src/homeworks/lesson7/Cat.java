package homeworks.lesson7;

import java.util.Random;

class Cat {

    final private String name;
    final private int volumeStomach;      // объем желудка
    private int satiety;            // сытость

    Cat(String name, int volumeStomach) {
        this.name = name;
        this.volumeStomach = volumeStomach;
        satiety = 0;
    }

    String getName() {
        return name;
    }

    int getSatiety() {
        return satiety;
    }

    int getVolumeStomach() {
        return volumeStomach;
    }

    void eat(Plate plate) {
        int hungry = volumeStomach - satiety;           // насколько голоден кот
        int food = plate.getFoodAmount();               // кол-ство еды на тарелке
        if(food >= hungry) {
            satiety = volumeStomach;                    // кот насытился
            plate.putFood(food - hungry);    // оставшаяся еда
        } else {
            satiety += food;                            // кот съел все что в миске
            plate.putFood(0);                // миска пуста
        }
    }

    void speak(String message) {
        System.out.println(name + ": " + message);
    }
}
