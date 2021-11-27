package controllers;

import form.Pedido;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController extends BaseController
{
    @FXML
    private VBox parentContainer;

    @FXML
    private void loadMenuView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/menuView.fxml"));
            Parent root = loader.load();
            Scene escena = new Scene(root);

            //Crear nuevo objeto pedido
            Pedido pedido = new Pedido();

            //Obtiene el controlador de la vista
            MenuViewController controller = (MenuViewController) loader.getController();
            controller.initialize(pedido, 0);

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
