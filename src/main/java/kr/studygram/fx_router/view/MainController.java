package kr.studygram.fx_router.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import kr.studygram.fx_router.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cynos07 on 2017-04-18.
 */
public class MainController implements Initializable{

    @FXML private Button btnGuestNetwork;
    @FXML private Button btnTimeLimit;
    @FXML private Button btnAddOn;
    private MainApp application;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnGuestNetwork.setOnAction(event -> handleBtnGuestNetwork(event));
        btnTimeLimit.setOnAction(event -> handleBtnTimeLimit(event));
        btnAddOn.setOnAction(event -> handleBtnAddOn(event));
    }

    public void handleBtnAddOn(ActionEvent event) {
        application.gotoAddOn();
    }

    public void handleBtnGuestNetwork(ActionEvent event)
    {
        application.gotoGuestNetwork();
    }

    public void handleBtnTimeLimit(ActionEvent event) { application.gotoTimeLimit(); }

    public void setApp(MainApp application) {
        this.application = application;
    }
}