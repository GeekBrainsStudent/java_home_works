package homeworks2.lesson6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EchoClient extends Application {

    private Connection connect;     // коннект к серверу

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        try {
            connect = new Connection();         // создаем коннект
        } catch (IOException e) {
            connect = null;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();     // получаем контроллер
        controller.setConnection(connect);                      // передаем контроллеру коннект
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if(connect != null)
            connect.close();                    // если выходим из прилоржения, закрываем коннект
    }
}
