package kr.studygram.fx_router.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import kr.studygram.fx_router.MainApp;
import kr.studygram.fx_router.model.AddOn;
import kr.studygram.fx_router.model.AddOnList;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cynos07 on 2017-04-18.
 */
public class AddOnController implements Initializable{
    @FXML
    private JFXTreeTableView<AddOn> tableView;
    @FXML
    private JFXButton btnAddAddOn;
    @FXML
    private Button btnMain;
    @FXML
    private Button btnRefresh;

    private MainApp application;
    public ObservableList<AddOn> addOns;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRefresh.setOnAction(event ->handleBtnRefresh(event));
        btnAddAddOn.setOnAction(event -> handlebtnAddAddOn(event));
        btnMain.setOnAction(event -> handleBtnMain(event));
        JFXTreeTableColumn<AddOn, String> titleCol = new JFXTreeTableColumn<>("Title");
        titleCol.setPrefWidth(100);
        titleCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AddOn, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AddOn, String> param) {
                return param.getValue().getValue().titleProperty();
            }
        });

        JFXTreeTableColumn<AddOn, String> urlCol = new JFXTreeTableColumn<>("Url");
        urlCol.setPrefWidth(270);
        urlCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AddOn, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AddOn, String> param) {
                return param.getValue().getValue().urlProperty();
            }
        });

        addOns = FXCollections.observableArrayList();
        try{
            for(AddOn addOn : AddOnList.INSTANCE.getAddOnList())
            {
                addOns.add(addOn);
            }
        } catch(Exception e)
        {
            e.getStackTrace();
        }

        final TreeItem<AddOn> root = new RecursiveTreeItem<AddOn>(addOns, RecursiveTreeObject::getChildren);
        tableView.getColumns().setAll(titleCol, urlCol);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
        tableView.setEditable(true);
    }

    public void handleBtnRefresh(ActionEvent event) {
        addOns.clear();
        try{
            for(AddOn addOn : AddOnList.INSTANCE.getAddOnList())
            {
                addOns.add(addOn);
            }
        } catch(Exception e)
        {
            e.getStackTrace();
        }
    }

    public void addOnsAdd(AddOn addOn)
    {
        addOns.add(addOn);
    }

    public void handleBtnMain(ActionEvent event) {
        application.gotoMain();
    }

    public void handlebtnAddAddOn(ActionEvent event) {
        application.popupAddAddOn();
    }
    public void handleBtnSubmit(ActionEvent event) {
    }

    public void setApp(MainApp application) {
        this.application = application;
    }
}
