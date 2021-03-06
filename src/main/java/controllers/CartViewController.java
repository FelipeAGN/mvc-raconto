package controllers;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import form.Pedido;
import form.PedidoProduct;
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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.IntStream;

public class CartViewController extends BaseController
{
    @FXML
    private VBox parentContainer;

    @FXML
    private Label totalMountLabel;

    @FXML
    private VBox cartContainer;

    private Pedido pedido;

    public void initialize(Pedido pedido)
    {
        this.pedido = pedido;

        //Set total mount to label
        DecimalFormat formatter = new DecimalFormat("###,###");
        this.totalMountLabel.setText("$ " + formatter.format(this.pedido.getTotalBalance()));

        List<PedidoProduct> pedidoProductList = pedido.getProductList();

        for (PedidoProduct product: pedidoProductList)
        {
            //Agregar nuevo producto
            HBox productContainer = new HBox();
            Region leftRegion = new Region();
            VBox productImageContainer = new VBox();
            ImageView productImageView = new ImageView();
            VBox productInfoContainer = new VBox();
            Label productNameLabel = new Label();
            Label productCategoryLabel = new Label();
            Label productPriceLabel = new Label();
            MaterialDesignIconView subtractProductButton = new MaterialDesignIconView();
            Label productQuantityLabel = new Label();
            MaterialDesignIconView addProductButton = new MaterialDesignIconView();
            VBox removeProductButtonContainer = new VBox();
            MaterialDesignIconView removeProductButton = new MaterialDesignIconView();

            productContainer.setAlignment(Pos.CENTER);
            productContainer.setSpacing(15.0);
            productContainer.setPadding(new Insets(15, 0, 15, 0));
            productContainer.setStyle("-fx-background-color: #FFFFFF;");

            //Left region's settings
            leftRegion.setMinSize(48.0, 135.0);

            //Product image's settings
            productImageContainer.setMinSize(150.0, 135.0);
            productImageContainer.setAlignment(Pos.CENTER);
            productImageContainer.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 2; -fx-border-color: #63443C; -fx-border-radius: 2;");

            try
            {
                productImageView.setImage(new Image(new FileInputStream("src/main/resources/img/products/" + product.getProduct().getPathToImage())));
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

            productNameLabel.setText(product.getProduct().getName());
            productNameLabel.setMaxSize(217.0, 35.0);
            productNameLabel.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 24));
            productNameLabel.setTextFill(Color.rgb(99, 68, 60));

            String categoryText = "";

            //Set category to label
            switch (product.getProduct().getCategoria())
            {
                case 1:
                    categoryText = "S??ndwich";
                    break;

                case 2:
                    categoryText = "Postre";
                    break;

                case 3:
                    categoryText = "Bebida";
                    break;

                case 4:
                    categoryText = "Caf??";
                    break;
            }

            productCategoryLabel.setText("Categor??a: " + categoryText);
            productCategoryLabel.setMaxSize(217.0, 20.0);
            productCategoryLabel.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 14));
            productCategoryLabel.setTextFill(Color.rgb(91, 88, 88));

            productPriceLabel.setText("$ " + formatter.format(product.getProduct().getPrice()));
            productPriceLabel.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 28));
            productPriceLabel.setTextFill(Color.rgb(219, 103, 79));

            productInfoContainer.getChildren().addAll(productNameLabel, productCategoryLabel, productPriceLabel);

            //Subtract product button's settings
            subtractProductButton.setGlyphName("MINUS_CIRCLE");
            subtractProductButton.setSize("50");
            subtractProductButton.setFill(Color.rgb(219, 103, 79));
            subtractProductButton.setCursor(Cursor.HAND);

            subtractProductButton.setOnMousePressed(event -> {
                if (product.getCantidadProduct() > 1)
                {
                    product.lessCantidad();
                    productQuantityLabel.setText(Integer.toString(product.getCantidadProduct()));
                }

                updateTotalMount();
            });

            //Product quantity settings
            productQuantityLabel.setText(Integer.toString(product.getCantidadProduct()));
            productQuantityLabel.setMinSize(50.0, 58.0);
            productQuantityLabel.setMaxSize(50.0, 58.0);
            productQuantityLabel.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 40));
            productQuantityLabel.setTextFill(Color.rgb(99, 68, 60));
            productQuantityLabel.setAlignment(Pos.CENTER);

            //Add product button's settings
            addProductButton.setGlyphName("PLUS_CIRCLE");
            addProductButton.setSize("50");
            addProductButton.setFill(Color.rgb(209, 201, 38));
            addProductButton.setCursor(Cursor.HAND);

            addProductButton.setOnMousePressed(event -> {
                if (product.getCantidadProduct() < 99)
                {
                    System.out.println(product.getProduct().getName());
                    product.addCantidad();
                    productQuantityLabel.setText(Integer.toString(product.getCantidadProduct()));
                }

                updateTotalMount();
            });

            //Remove product button's settings
            removeProductButtonContainer.setMaxSize(38.0, 135.0);

            removeProductButton.setGlyphName("DELETE");
            removeProductButton.setSize("40");
            removeProductButton.setFill(Color.rgb(219, 103, 79));
            removeProductButton.setCursor(Cursor.HAND);

            removeProductButton.setOnMousePressed(event -> {
                MaterialDesignIconView button = (MaterialDesignIconView) event.getSource();
                HBox container = (HBox) ((VBox) button.getParent()).getParent();

                //Eliminar del elemento (base de datos)
                this.pedido.removeProductToPedido(product.getProduct());
                this.cartContainer.getChildren().remove(container);
                updateTotalMount();
            });

            removeProductButtonContainer.getChildren().add(removeProductButton);

            // Add all elements to product container
            productContainer.getChildren().addAll(
                    leftRegion,
                    productImageContainer,
                    productInfoContainer,
                    subtractProductButton,
                    productQuantityLabel,
                    addProductButton,
                    removeProductButtonContainer
            );

            this.cartContainer.getChildren().add(productContainer);
        }
    }

    private void updateTotalMount()
    {
        DecimalFormat formatter = new DecimalFormat("###,###");
        this.totalMountLabel.setText("$ " + formatter.format(this.pedido.getTotalBalance()));
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
    private void checkOutOrder()
    {
        //Incluir logica antes, abrir alerta simulando pago

        //Sleep(5)
        loadOrderSuccessfulView();
    }

    private void loadOrderSuccessfulView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/orderSuccessfulView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) parentContainer.getScene().getWindow();
            window.setScene(scene);
            window.show();
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
