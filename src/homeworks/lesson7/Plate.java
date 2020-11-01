package homeworks.lesson7;

class Plate {
    final private int capacity;
    private int foodAmount;

    Plate(int capacity) {
        this.capacity = capacity;
    }

    void putFood(int foodAmount) {
        this.foodAmount = Math.min(foodAmount, capacity);
    }

    int getFoodAmount() {
        return foodAmount;
    }
}
