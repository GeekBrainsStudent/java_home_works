package homeworks2.lesson4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    TextField textField;
    @FXML
    Button button;
    @FXML
    ListView<String> listView;
    @FXML
    TableView<TableItems> tableView;
    @FXML
    TableColumn<TableItems, String> wordCol;
    @FXML
    TableColumn<TableItems, Integer> countCol;

    private final ObservableList<String> listItems = FXCollections.observableArrayList();;
    private final ObservableList<TableItems> tableItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        wordCol.setText("Words");
        wordCol.setCellValueFactory(new PropertyValueFactory<>("word"));

        countCol.setText("Count");
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        listView.setItems(listItems);
        tableView.setItems(tableItems);

        addWordsToList("Java");
        addWordsToTable("Java");
        addWordsToList("C#");
        addWordsToTable("C#");
    }

    @FXML
    public void addWord() {
        String word = getWord();
        if(checkWord(word)) {
            addWordsToTable(word);
            addWordsToList(word);
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

    private String getWord() {
        return textField.getText();
    }

    private boolean checkWord(String word) {
        if(word.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Нельзя передавать пустые строки.", ButtonType.OK);
            alert.setHeaderText("Ошибка ввода.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private void addWordsToList(String word) {
        listItems.add(word);
        textField.clear();
    }

    private void addWordsToTable(String word) {
        for(TableItems item : tableItems) {
            if(item.getWord().equals(word)) {
                item.setCount(item.getCount() + 1);
                return;
            }
        }
        tableItems.add(new TableItems(word,1));
    }
}
