package kr.studygram.fx_router.view;

import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import kr.studygram.fx_router.MainApp;
import kr.studygram.fx_router.model.TimeLimit;
import kr.studygram.fx_router.network.Server;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cynos07 on 2017-04-18.
 */
public class TimeLimitController implements Initializable{
    @FXML private JFXTimePicker timePickerStart;
    @FXML private JFXTimePicker timePickerEnd;
    @FXML private Button btnSubmit;
    @FXML private Button btnMain;
    @FXML private Label alertMessage;

    private MainApp application;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        timePickerStart.set
        btnSubmit.setOnAction(event->handleBtnSubmit(event));
        btnMain.setOnAction(event->handleBtnMain(event));
    }

    public void handleBtnSubmit(ActionEvent event) {
        System.out.println(timePickerStart);
        if(timePickerStart.getValue() == null|| timePickerEnd.getValue() == null)
        {
            alertMessage.setText("잘못된 시간입니다");
            return;
        }
        else if (timePickerEnd.getValue().compareTo(timePickerStart.getValue()) <= 0)
        {
            alertMessage.setText("시작시간이 잘못되었습니다.");
            return;
        }
        TimeLimit timeLimit = TimeLimit.getInstance();
        timeLimit.init(timePickerStart.getValue().toString(), timePickerEnd.getValue().toString());
        alertMessage.setText("사용시간이 설정되었습니다");
        Server.getInstance().sendMessage(timePickerStart.getValue() + " ~ "+timePickerEnd.getValue()+" 사용시간 제한 설정.");
    }

    public void handleBtnMain(ActionEvent event) {
        application.gotoMain();
    }

    public void setApp(MainApp application) {
        this.application = application;
    }
}
