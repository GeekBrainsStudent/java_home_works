package homeworks2.lesson5;

public class Main {
    static final int size = 10_000_000;
    static final int half = size/2;
    static final float[] arr = new float[size];

    public static void main(String[] args) {

        init();

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
            arr[i] = setNewVal(arr[i], i);
        System.out.println("1 поток: " + (System.currentTimeMillis() - startTime));


        init();

        startTime = System.currentTimeMillis();

        float[] arr1 = new float[half];
        float[] arr2 = new float[half];

        System.arraycopy(arr,0,arr1,0,half);
        System.arraycopy(arr,half,arr2,0,half);
        
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < half; i++)
                arr1[i] = setNewVal(arr1[i], i);
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < half; i++)
                arr2[i] = setNewVal(arr2[i], i + half);
        });
        t2.start();

        // Ждем завершения потоков
        while(t1.isAlive() || t2.isAlive()) { }

        System.arraycopy(arr1,0,arr,0,half);
        System.arraycopy(arr2,0,arr,half,half);

        System.out.println("2 потока: " + (System.currentTimeMillis() - startTime));
    }

    private static void init() {
        for(int i= 0; i < size; i++)
            arr[i] = 1.0f;
    }

    private static float setNewVal(float val, int i) {
        return (float) (val * Math.sin(0.2f + i/5) *
                Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
    }
}