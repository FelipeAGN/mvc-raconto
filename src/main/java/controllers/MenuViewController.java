package controllers;

import form.CategoriaProduct;
import form.Pedido;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    private Pedido pedido;

    public void initialize(Pedido pedido, double money)
    {
        double dinero = 1000.0;
        this.pedido = pedido;
        DecimalFormat formatter = new DecimalFormat("###,###");
        this.totalMountLabel.setText("$ " + formatter.format(dinero));
        //this.totalMountLabel.setText("$ " + formatter.format(this.pedido.getTotalBalance()));
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
        loadProductsView(CategoriaProduct.CAFE);
    }

    @FXML
    private void loadPostresView()
    {
        loadProductsView(CategoriaProduct.POSTRE);
    }

    @FXML
    private void loadSandwichesView()
    {
        loadProductsView(CategoriaProduct.SANDWICH);
    }

    @FXML
    private void loadBebidasView()
    {
        loadProductsView(CategoriaProduct.BEBIDA);
    }

    private void loadProductsView(CategoriaProduct category)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/productsView.fxml"));
            Parent root = loader.load();
            Scene escena = new Scene(root);

            //Carga el controlador de la vista
            ProductsViewController controller = (ProductsViewController) loader.getController();
            controller.initialize(this.pedido, category, 2000);

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
    private void loadCartView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/cartView.fxml"));
            Parent root = loader.load();
            Scene escena = new Scene(root);

            //Carga el controlador de la vista
            CartViewController controller = (CartViewController) loader.getController();
            controller.initialize(this.pedido,2000);

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
