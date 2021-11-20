package controllers;

import form.Client;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainViewController
{
    //Model
    Client account;

    //View nodes
    @FXML
    private Label accountHolder;

    @FXML
    private Label accountBalance;

    @FXML
    private TextField amountTextField;

    public void initialize()
    {
//        Get model
//        Client accountModel = new Client("Elliot Alderson", 6626,
//                                                1000d);
//
//        Link model with view
//        accountHolder.textProperty().bind(new SimpleStringProperty(account.getAccountHolder()));
//        accountBalance.textProperty().bind(new SimpleDoubleProperty(account.getAccountBalance()));
    }

    @FXML
    private void handleDeposit(Event event)
    {
//        account.deposit(getAmount());
//        event.consume();
    }

    private double getAmount()
    {
        return 50.0;
    }
}
