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

public class MenuViewController extends BaseController
{
    @FXML
    private VBox parentContainer;

    @FXML
    private Label totalMountLabel;

    private double dinero;

    public void initialize(double dinero)
    {
        this.dinero = dinero;
        DecimalFormat formatter = new DecimalFormat("###,###");
        this.totalMountLabel.setText("$ " + formatter.format(this.dinero));
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
    private void loadCafesView()
    {
        String category = "Cafés";
        loadProductsView(category);
    }

    @FXML
    private void loadPostresView()
    {
        String category = "Postres";
        loadProductsView(category);
    }

    @FXML
    private void loadSandwichesView()
    {
        String category = "Sándwiches";
        loadProductsView(category);
    }

    @FXML
    private void loadBebidasView()
    {
        String category = "Bebidas";
        loadProductsView(category);
    }

    private void loadProductsView(String category)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/productsView.fxml"));
            Parent root = loader.load();
            Scene escena = new Scene(root);

            //Carga el controlador de la vista
            ProductsViewController controller = (ProductsViewController) loader.getController();
            controller.initialize(category, 2000);

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
