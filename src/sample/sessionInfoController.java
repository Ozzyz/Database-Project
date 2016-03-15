package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class sessionInfoController extends SceneController implements Initializable{

    public TableView infoTableView;
    public TableColumn exerciseColumn;
    public TableColumn setColumn;
    public TableColumn repColumn;
    public TableColumn weightColumn;

    public void backButtonClickd(ActionEvent actionEvent) {
        super.backButtonClicked(actionEvent);
    }

    public void registerButtonClicked(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList obs = FXCollections.observableArrayList(dbh.getØktData(1));
            infoTableView.getItems().add(obs);

    }
}
