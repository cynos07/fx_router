package kr.studygram.fx_router.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import kr.studygram.fx_router.MainApp;
import kr.studygram.fx_router.model.GuestNetwork;
import kr.studygram.fx_router.network.Server;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cynos07 on 2017-04-18.
 */
public class GuestNetworkController implements Initializable{
    @FXML private StackPane root;
    @FXML private Pane guestNetwork;
    @FXML private ChoiceBox frequencyBox;
    @FXML private TextField ssid;
    @FXML private Label labelPassword;
    @FXML private PasswordField password;
    @FXML private Button btnSubmit;
    @FXML private Button btnMain;
    @FXML private Label alertMessage;
    @FXML private ChoiceBox secureLevel;
    private MainApp application;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSubmit.setOnAction(event->handleBtnSubmit(event));
        btnMain.setOnAction(event->handleBtnMain(event));
        secureLevel.setItems(FXCollections.observableArrayList(
                "개방형", "WPA/WPA2"));
        secureLevel.getSelectionModel().selectFirst();
        secureLevel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue == "WPA/WPA2")
                {
                    labelPassword.setDisable(false);
                    password.setDisable(false);
                }
                else{
                    labelPassword.setDisable(true);
                    password.setDisable(true);
                }
                ssid.setText("");
                password.setText("");
            }
        });



        frequencyBox.setItems(FXCollections.observableArrayList(
                "2.4GHz", "5.0GHz"));
        frequencyBox.getSelectionModel().selectFirst();
        frequencyBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                ssid.setText("");
                password.setText("");
            }
        });
    }

    public void handleBtnSubmit(ActionEvent event) {
        if(secureLevel.getSelectionModel().getSelectedItem().toString() =="개방형")
        {
            if(ssid.getText().isEmpty())
            {
                alertMessage.setText("잘못된 SSID입니다.");
                return;
            }
            GuestNetwork.getInstance().init(Double.parseDouble(frequencyBox.getSelectionModel().getSelectedItem().toString().substring(0,2)), ssid.getText(), "");
        }
        else{
            if(ssid.getText().isEmpty() || password.getText().isEmpty())
            {
                alertMessage.setText("잘못된 SSID나 Password입니다");
                return;
            }
            GuestNetwork.getInstance().init(Double.parseDouble(frequencyBox.getSelectionModel().getSelectedItem().toString().substring(0,2)), ssid.getText(), password.getText());
        }
        alertMessage.setText("GuestWiFi가 생성되었습니다.");
        Server.getInstance().sendMessage(ssid.getText()+" 게스트 네트워크가 생성되었습니다.");
    }

    public void handleBtnMain(ActionEvent event) {
        application.gotoMain();
    }

    public void setApp(MainApp application) {
        this.application = application;
    }
}
