package controllers;

import com.jfoenix.controls.JFXButton;
import form.Client;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class CartViewController extends BaseController
{
    @FXML
    private VBox parentContainer;

    @FXML
    private Label totalMountLabel;

    @FXML
    private Label categoryLabel;

    private String category;
    private double mount;

    public void initialize(String category, double mount)
    {
        this.category = category;
        this.mount = mount;

        //Set category to label
        this.categoryLabel.setText(this.category);

        //Set total mount to label
        DecimalFormat formatter = new DecimalFormat("###,###");
        this.totalMountLabel.setText("$ " + formatter.format(this.mount));
    }

    @FXML
    private void cancelOrder()
    {
        if (showCancelConfirmAlert())
            loadMainView();
    }

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

    @FXML
    private void loadMenuView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/menuView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Obtiene el controlador de la vista
            MenuViewController controller = (MenuViewController) loader.getController();
            controller.initialize(this.mount);

            Stage window = (Stage) parentContainer.getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        catch (IOException | IllegalStateException exception)
        {
            showAlertException(exception);
        }
    }
}
