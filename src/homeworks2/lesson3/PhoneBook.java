package homeworks2.lesson3;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    HashMap<Integer,String> data;

    public PhoneBook() {
        data = new HashMap<>();
    }

    public void add(final String name, final int number) {
        data.put(number, name);
    }

    public void printPhone(String name) {
        for(Map.Entry<Integer,String> entry : data.entrySet()) {
            if(entry.getValue().equals(name))
                System.out.println(name + ": " + entry.getKey());
        }

    }
}
