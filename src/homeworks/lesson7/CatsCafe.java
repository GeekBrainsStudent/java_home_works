package homeworks.lesson7;

import java.util.Random;

class CatsCafe {

    Random random;
    final private int numbCats = 10;
    final private int numbPlates = 13;

    final private Cat[] cats;
    final private Plate[] plates;

    CatsCafe() {
        random = new Random();
        cats = createCats(numbCats);
        plates = createPlates(numbPlates);

    }

    private Cat[] createCats(int numbCats) {
        Cat[] cats = new Cat[numbCats];
        for(int i = 0; i < numbCats; i++) {
            cats[i] = new Cat(setCatName(cats), setCatVolumeStomach());
        }
        return cats;
    }

    private String setCatName(Cat[] cats) {
        int i;
        StringBuilder name = new StringBuilder();
        do {
            i = random.nextInt(Data.catsNames.length);
            name.delete(0,name.length()).append(Data.catsNames[i]);
        }
        while(!isNameCorrect(cats, Data.catsNames[i]));
        return name.toString();
    }

    // проверка чтобы имя не повторялось
    private boolean isNameCorrect(Cat[] cats, String name) {
        for(Cat cat : cats) {
            if(cat == null)
                break;
            if(cat.getName().equals(name))
                return false;
        }
        return true;
    }

    private int setCatVolumeStomach() {
        return random.nextInt(11) + 10; // от 10 до 20
    }

    private Plate[] createPlates(int numbPlates) {
        Plate[] plates = new Plate[numbPlates];
        for(int i = 0; i < numbPlates; i++) {
            plates[i] = new Plate(setPlateCapacity());
        }
        return plates;
    }

    private int setPlateCapacity() {
        return random.nextInt(15) + 8;  // от 8 до 22
    }

    void kittyKittyKitty() {
        StringBuilder message = new StringBuilder();
        int i = 0, j = 0;
        for( ; i < numbCats; i++) {
            message.delete(0, message.length())
                    .append("Я остался голодным. Вам надо было отрыть кафе для собак!");
            for( ; j < numbPlates; j++) {
                cats[i].eat(plates[j]);
                if(cats[i].getVolumeStomach() == cats[i].getSatiety()) {
                    message.delete(0, message.length()).append("Спасибо, прекрасное кафе!");
                    break;
                }
            }
            cats[i].speak(message.toString());
        }
    }

    void putFood() {
        for(int i = 0; i < numbPlates; i++) {
            plates[i].putFood(setFoodAmount());
        }
    }

    private int setFoodAmount() {
        return random.nextInt(20) + 6;  // от 6 до 25
    }
}
