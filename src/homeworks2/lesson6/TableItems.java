package homeworks2.lesson6;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TableItems {

    public SimpleStringProperty message;
    public SimpleStringProperty date;

    public String getMessage() {
        return message.get();
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }



    public void setDate(String date) {
        this.date.set(date);
    }




    public TableItems(String word, String date) {
        this.message = new SimpleStringProperty(word);
        this.date = new SimpleStringProperty(date);
    }


}
