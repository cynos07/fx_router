package kr.studygram.fx_router.view;

import com.google.gson.JsonObject;
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
import kr.studygram.fx_router.network.Client;

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
        JsonObject ssid_req = new JsonObject();
        ssid_req.addProperty("command", "ssid");
        ssid_req.addProperty("value", ssid.getText());


        JsonObject pass_req = new JsonObject();
        pass_req.addProperty("command", "pw");
        pass_req.addProperty("value", password.getText());

        JsonObject frequency_req = new JsonObject();
        frequency_req.addProperty("command", "freq");
        frequency_req.addProperty("value", frequencyBox.getSelectionModel().getSelectedItem().toString());

        JsonObject secureLevel_req = new JsonObject();
        secureLevel_req.addProperty("command", "encrypt");
        secureLevel_req.addProperty("value", secureLevel.getSelectionModel().getSelectedItem().toString());

        Client.getInstance().sendMessage(ssid_req.toString());
        Client.getInstance().sendMessage(pass_req.toString());
        Client.getInstance().sendMessage(frequency_req.toString());
        Client.getInstance().sendMessage(secureLevel_req.toString());
    }

    public void handleBtnMain(ActionEvent event) {
        application.gotoMain();
    }

    public void setApp(MainApp application) {
        this.application = application;
    }
}
