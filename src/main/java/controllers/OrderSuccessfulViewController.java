package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderSuccessfulViewController extends BaseController
{
    @FXML
    private VBox parentContainer;

    @FXML
    private void loadMainView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/mainView.fxml"));
            Parent root = loader.load();
            Scene escena = new Scene(root);
            Stage ventana = (Stage) parentContainer.getScene().getWindow();
            ventana.setScene(escena);
            ventana.show();
        }
        catch (IOException | IllegalStateException exception)
        {
            showAlertException(exception);
        }
    }
}
