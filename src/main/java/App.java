import DB.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Objects;

public class App extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainView.fxml"));
        primaryStage.setTitle("Raconto Token Autoservice");
        primaryStage.setScene(new Scene(root, 850, 1000));
        primaryStage.show();


        //System.out.println("Hola mundo desde start()");
    }

    public static void main(String[] args)
    {
        Connection connection;
        connection = DataBase.connection();

        launch(args);
        //System.out.println("Hola mundo desde main()");
    }
}
