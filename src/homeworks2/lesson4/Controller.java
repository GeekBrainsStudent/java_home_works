package homeworks2.lesson4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {

    @FXML private ListView<String> users_list;
    @FXML private TableView<TableItems> messages_table;
    @FXML private TableColumn<TableItems, String> mess_col;
    @FXML private TableColumn<TableItems, String> date_col;
    @FXML private TextField type_message;
    @FXML private Button send_message;

    private final ObservableList<String> users = FXCollections.observableArrayList();;
    private final ObservableList<TableItems> messages = FXCollections.observableArrayList();


    @FXML
    public void initialize() {

        mess_col.setText("Сообщения");
        mess_col.setCellValueFactory(cell -> cell.getValue().messageProperty());
        mess_col.setCellFactory(param -> {
            TableCell<TableItems, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.textProperty().bind(cell.itemProperty());
            text.wrappingWidthProperty().bind(mess_col.widthProperty());
            return cell;
        });


        date_col.setText("Время");
        date_col.setCellValueFactory(cell -> cell.getValue().dateProperty());

        messages.add(new TableItems("Меркель:\nМне приснилось, что меня назначили президентом Земли!",
                "23:35:02"));
        messages.add(new TableItems("Трамп:\nА мне приснилось, что меня назначили президентом Вселенной!",
                "23:35:05"));
        messages.add(new TableItems("Путин:\nА мне приснилось, что я никого не утвердил.",
                "23:35:08"));

        messages_table.setItems(messages);

        users.addAll("Путин", "Меркель", "Трамп");
        users_list.setItems(users);
    }

    @FXML
    public void send_mess() {
        String mess = type_message.getText();
        if(!mess.isBlank()) {
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int seconds = calendar.get(Calendar.SECOND);
            String date  = hour + ":" + minute + ":" + seconds;
            type_message.clear();
            messages.add(new TableItems(mess, date));
        }
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Java2");
        alert.setContentText("lesson #4");
        alert.showAndWait();
    }
}
