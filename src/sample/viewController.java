package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewController extends SceneController implements Initializable{

    @FXML
    ListView<String> sessionListView;
    @FXML
    ListView<String> exerciseListView;
    @FXML
    TableView<String> infoTableView;

    public void backButtonClickd(ActionEvent actionEvent) {
        super.backButtonClicked(actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO Fetch data from db
        ArrayList<String> liste = new ArrayList<>();
        liste.add("Item1");
        liste.add("Item2");
        liste.add("Item3");
        super.populateListView(liste, sessionListView);
        super.populateListView(liste, exerciseListView);
    }


}
