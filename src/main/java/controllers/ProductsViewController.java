package controllers;

import DAO.ProductDAOImpl;
import com.jfoenix.controls.JFXButton;
import form.CategoriaProduct;
import form.Pedido;
import form.PedidoProduct;
import form.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.IntStream;

public class ProductsViewController extends BaseController
{
    @FXML
    private VBox parentContainer;

    @FXML
    private Label totalMountLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private VBox productsContainer;

    //Solo testing
    @FXML
    private JFXButton button;

    private Pedido pedido;
    private List<Product> productsList;

    public void initialize(Pedido pedido, CategoriaProduct category)
    {
        this.pedido = pedido;

        String categoryText = "";

        //Set category to label
        switch (category)
        {
            case CAFE:
                categoryText = "Cafés";
                break;

            case BEBIDA:
                categoryText = "Bebidas";
                break;

            case POSTRE:
                categoryText = "Postres";
                break;

            case SANDWICH:
                categoryText = "Sándwiches";
                break;
        }

        this.categoryLabel.setText(categoryText);

        //Set total mount to label
        DecimalFormat formatter = new DecimalFormat("###,###");
        this.totalMountLabel.setText("$ " + formatter.format(this.pedido.getTotalBalance()));

        //Get Products
        ProductDAOImpl productDAO = new ProductDAOImpl();

        System.out.println(category.getCategoriaProductValue());

        this.productsList = productDAO.listProductsByCategoria(category.getCategoriaProductValue());

        for (Product product: this.productsList)
        {
            //Agregar nuevo producto
            HBox productContainer = new HBox();
            Region leftRegion = new Region();
            VBox productImageContainer = new VBox();
            ImageView productImageView = new ImageView();
            VBox productInfoContainer = new VBox();
            Label productNameLabel = new Label();
            Label productPriceLabel = new Label();
            JFXButton addProductButton = new JFXButton();
            Region rightRegion = new Region();

            productContainer.setAlignment(Pos.CENTER);
            productContainer.setSpacing(15.0);
            productContainer.setPadding(new Insets(15, 0, 15, 0));
            productContainer.setStyle("-fx-background-color: #FFFFFF;");

            //Left region's settings
            leftRegion.setMinSize(50.0, 135.0);

            //Product image's settings
            productImageContainer.setMinSize(150.0, 135.0);
            productImageContainer.setAlignment(Pos.CENTER);
            productImageContainer.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 2; -fx-border-color: #63443C; -fx-border-radius: 2;");

            try
            {
                productImageView.setImage(new Image(new FileInputStream("src/main/resources/img/products/" + product.getPathToImage())));
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Error al encontrar la imagen");
                System.out.println(System.getProperty("user.dir"));
            }
            productImageView.setFitWidth(140.0);
            productImageView.setFitHeight(125.0);

            productImageContainer.getChildren().add(productImageView);

            // Product info's settings
            productInfoContainer.setMinSize(209.0, 135.0);
            productInfoContainer.setSpacing(10.0);

            productNameLabel.setText(product.getName());
            productNameLabel.setMaxSize(209.0, 35.0);
            productNameLabel.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 24));
            productNameLabel.setTextFill(Color.rgb(99, 68, 60));

            productPriceLabel.setText("$ " + formatter.format(product.getPrice()));
            productPriceLabel.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 28));
            productPriceLabel.setTextFill(Color.rgb(219, 103, 79));

            productInfoContainer.getChildren().addAll(productNameLabel, productPriceLabel);

            //Add button's settings
            addProductButton.setMinSize(154.0, 50.0);
            addProductButton.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
            addProductButton.setTextAlignment(TextAlignment.CENTER);
            addProductButton.setTextFill(Color.rgb(255, 255, 255));
            addProductButton.setCursor(Cursor.HAND);

            if (this.pedido.getProductByName(product.getName()) == null)
            {
                addProductButton.setText("AGREGAR");
                addProductButton.setStyle("-fx-background-color: #D1C926; -fx-border-radius: 10;");
            }
            else
            {
                addProductButton.setText("QUITAR");
                addProductButton.setStyle("-fx-background-color: #DB674F; -fx-border-radius: 10;");
            }


            addProductButton.setOnAction(event -> {
                String radiusStyle = "-fx-border-radius: 10;";

                //Get product from database
                // Show alert window

                if (this.pedido.getProductByName(product.getName()) != null)
                {
                    //Quitar producto al pedido
                    this.pedido.removeProductToPedido(product);
                    addProductButton.setText("AGREGAR");
                    addProductButton.setStyle("-fx-background-color: #D1C926;" + radiusStyle);
                }
                else
                {
                    //Agregar producto al pedido
                    this.pedido.addProductToPedido(product, 1);
                    addProductButton.setText("QUITAR");
                    addProductButton.setStyle("-fx-background-color: #DB674F;" + radiusStyle);
                }

                updateTotalMount();
            });

            // Right Region
            rightRegion.setMinSize(50.0, 135.0);

            productContainer.getChildren().addAll(leftRegion, productImageContainer, productInfoContainer, addProductButton, rightRegion);

            this.productsContainer.getChildren().add(productContainer);
        };
    }

    private void updateTotalMount()
    {
        DecimalFormat formatter = new DecimalFormat("###,###");
        this.totalMountLabel.setText("$ " + formatter.format(this.pedido.getTotalBalance()));
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
            controller.initialize(this.pedido);

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
