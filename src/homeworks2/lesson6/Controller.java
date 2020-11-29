package homeworks2.lesson6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {

    @FXML private ListView<String> users_list;
    @FXML private TableView<TableItems> messages_table;
    @FXML private TableColumn<TableItems, String> mess_col;
    @FXML private TableColumn<TableItems, String> date_col;
    @FXML private TextField type_message;

    private final ObservableList<String> users = FXCollections.observableArrayList();;
    private final ObservableList<TableItems> messages = FXCollections.observableArrayList();

    private Connection connect;

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

        messages_table.setItems(messages);

        users_list.setItems(users);
    }

    @FXML
    public void send_mess() {
        String mess = type_message.getText();
        if(connect == null) {
            showMessage(Alert.AlertType.ERROR, "Нет соединения с сервером");
            return;
        }
        if(!mess.isBlank()) {
            type_message.clear();
            try {
                connect.sendMess(mess);
            } catch (IOException e){
                showMessage(Alert.AlertType.ERROR, e.getMessage());
            }
        }
    }

    public void setConnection(Connection connect) {
        this.connect = connect;
        if(this.connect != null)
            waitMessageFromServer();
    }

    private void addMessage(String mess) {
        if(!mess.isBlank()) {
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int seconds = calendar.get(Calendar.SECOND);
            String date  = hour + ":" + minute + ":" + seconds;
            messages.add(new TableItems(mess, date));
        }
    }

//    Запускает процесс ожидания сообщений от сервера
    private void waitMessageFromServer() {
        Thread t = new Thread(() -> {
            while(true) {
                try{
                    String mess = connect.getIn().readUTF();
                    if(!mess.isBlank())
                        addMessage(mess);

                } catch (IOException e) {
                    showMessage(Alert.AlertType.ERROR, e.getMessage());
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void showMessage(Alert.AlertType type, String contentText) {
        Alert alert = new Alert(type);
        alert.setContentText(contentText);
        String headerText = switch (type) {
            case NONE -> null;
            case INFORMATION -> "Информация";
            case WARNING -> "Внимание!";
            case CONFIRMATION -> "Вы уверены?";
            case ERROR -> "Ошибка";
        };
        alert.setHeaderText(headerText);
        alert.showAndWait();
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
