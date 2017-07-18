package kr.studygram.fx_router.view;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kr.studygram.fx_router.MainApp;
import kr.studygram.fx_router.model.AddOnList;
import kr.studygram.fx_router.network.Client;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cynos07 on 2017-04-26.
 */
public class AddAddOnController implements Initializable {
    @FXML
    private TextField title;
    @FXML
    private TextField url;
    @FXML
    private Button btnSubmit;
    @FXML
    private Label alertMessage;

    private MainApp application;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSubmit.setOnAction(event -> handleBtnSubmit(event));
    }

    public void handleBtnSubmit(ActionEvent event) {
        if(title.getText().equals("")||url.getText().equals(""))
        {
            alertMessage.setText("값을 확인해주세요.");
            return;
        }
        AddOnList.INSTANCE.add(title.getText(), url.getText());
        alertMessage.setText("추가되었습니다.");
        JsonObject json = new JsonObject();
        json.addProperty("command", "startPage");
        json.addProperty("value", url.getText());
        Client.getInstance().sendMessage(json.toString());
    }

    public void setApp(MainApp application) {
        this.application = application;
    }
}