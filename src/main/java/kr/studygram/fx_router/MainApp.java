package kr.studygram.fx_router;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.studygram.fx_router.network.Server;
import kr.studygram.fx_router.view.AddOnController;
import kr.studygram.fx_router.view.GuestNetworkController;
import kr.studygram.fx_router.view.MainController;
import kr.studygram.fx_router.view.TimeLimitController;

/**
 * Created by cynos07 on 2017-04-18.
 */
public class MainApp extends Application {
    private Group root = new Group();

    public Parent createContent() {
        gotoMain();
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Router");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        Thread server = new Thread(Server.getInstance());
        server.start();
    }

    public void gotoAddOn() {
        try {
            AddOnController addOn = (AddOnController) replaceSceneContent("AddOn.fxml");
            addOn.setApp(this);
        } catch (Exception ex) {
//            Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoMain() {
        try {
            MainController main = (MainController) replaceSceneContent("Main.fxml");
            main.setApp(this);
        } catch (Exception ex) {
//            Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoGuestNetwork() {
        try {
            GuestNetworkController login = (GuestNetworkController) replaceSceneContent("GuestNetwork.fxml");
            login.setApp(this);
        } catch (Exception ex) {
//            Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoTimeLimit() {
        try {
            TimeLimitController timeLimit = (TimeLimitController) replaceSceneContent("TimeLimit.fxml");
            timeLimit.setApp(this);
        } catch (Exception ex) {
//            Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void popupAddAddOn()
    {
        try {
            popup("Add Add-on", "AddAddOn.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popup(String title,String fxml) throws Exception{
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(MainApp.class.getClassLoader().getResource(fxml))));
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initOwner(btn1.getScene().getWindow());
        stage.showAndWait();
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getClassLoader().getResource(fxml));
        Pane page = loader.load();
        root.getChildren().clear();
        root.getChildren().addAll(page);
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
