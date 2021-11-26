package controllers;

import com.jfoenix.controls.JFXButton;
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

    private String category;
    private double mount;

    private boolean state;

    public void initialize(String category, double mount)
    {
        this.category = category;
        this.mount = mount;
        this.state = false;

        //Set category to label
        this.categoryLabel.setText(this.category);

        //Set total mount to label
        DecimalFormat formatter = new DecimalFormat("###,###");
        this.totalMountLabel.setText("$ " + formatter.format(this.mount));

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
            productImageView.setImage(new Image(new FileInputStream("src/main/resources/img/products/barros.jpg")));
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

        productNameLabel.setText("Barros Luco");
        productNameLabel.setMaxSize(209.0, 35.0);
        productNameLabel.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 24));
        productNameLabel.setTextFill(Color.rgb(99, 68, 60));

        productPriceLabel.setText("$ 4,500");
        productPriceLabel.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 28));
        productPriceLabel.setTextFill(Color.rgb(219, 103, 79));

        productInfoContainer.getChildren().addAll(productNameLabel, productPriceLabel);

        //Add button's settings
        addProductButton.setMinSize(154.0, 50.0);
        addProductButton.setText("AGREGAR");
        addProductButton.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        addProductButton.setTextAlignment(TextAlignment.CENTER);
        addProductButton.setTextFill(Color.rgb(255, 255, 255));
        addProductButton.setCursor(Cursor.HAND);
        addProductButton.setStyle("-fx-background-color: #D1C926; -fx-border-radius: 10;");

        addProductButton.setOnAction(event -> {
            JFXButton button = (JFXButton) event.getSource();
            VBox container = (VBox) ((HBox) button.getParent()).getChildren().get(2);
            String productName = ((Label) container.getChildren().get(0)).getText();
            String radiusStyle = "-fx-border-radius: 10;";

            if (productName == null)
                return;

            System.out.println(productName);

            //Get product from database

            // Show alert window

            if (this.state)
            {
                button.setText("AGREGAR");
                button.setStyle("-fx-background-color: #D1C926;" + radiusStyle);
            }
            else
            {
                button.setText("QUITAR");
                button.setStyle("-fx-background-color: #DB674F;" + radiusStyle);
            }

            this.state = !this.state;
        });

        // Right Region
        rightRegion.setMinSize(50.0, 135.0);

        productContainer.getChildren().addAll(leftRegion, productImageContainer, productInfoContainer, addProductButton, rightRegion);

        this.productsContainer.getChildren().add(productContainer);
    }

    @FXML
    private void toggleProduct()
    {
        if (this.state)
        {
            this.button.setText("AGREGAR");
            this.button.setStyle("-fx-background-color: #D1C926;");
        }
        else
        {
            this.button.setText("QUITAR");
            this.button.setStyle("-fx-background-color: #DB674F;");
        }

        this.state = !this.state;
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
