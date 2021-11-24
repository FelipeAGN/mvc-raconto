package controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class BaseController
{
    //Muestra un cuadro de dialogo, donde pide confirmación para cancelar el pedido
    protected boolean showCancelConfirmAlert()
    {
        Alert window = new Alert(Alert.AlertType.CONFIRMATION);

        window.setTitle("Confirmar Cancelación de Pedido");
        window.initStyle(StageStyle.UTILITY);
        window.setContentText("¿Realmente desea cancelar este pedido?");

        Optional<ButtonType> option = window.showAndWait();

        return option.get() == ButtonType.OK;
    }

    protected void showAlertException(Exception exception)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Alerta Excepción");
        alert.setHeaderText(exception.getMessage());
        alert.setContentText(exception.toString());

        //Se imprime el stacktrace de la excepcion en un cajón expandible de texto
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);

        TextArea text = new TextArea(sw.toString());
        text.setEditable(false);
        text.setWrapText(true);
        text.setMaxWidth(Double.MAX_VALUE);
        text.setMaxHeight(Double.MAX_VALUE);

        GridPane.setVgrow(text, Priority.ALWAYS);
        GridPane.setHgrow(text, Priority.ALWAYS);
        GridPane content = new GridPane();

        content.setMaxWidth(Double.MAX_VALUE);
        content.add(new Label("El Stacktrace de la excepción fue:"),0,0);
        content.add(text,0, 1);

        //Se ajusta el texto en la alerta y se muestra por pantalla
        alert.getDialogPane().setExpandableContent(content);
        alert.showAndWait();
    }
}
