package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ozzy on 14.03.2016.
 */
public class programController extends SceneController implements Initializable{
    @FXML public ListView programListView;
    @FXML ListView<String> muscleListView;
    List<String> muscleList = Arrays.asList("ben", "arm", "sko");
    List<String> programList = new ArrayList<>();


    public void populateListView(){
        ObservableList<String> items = FXCollections.observableArrayList (
                "Test1", "Test2", "ASD", "123");
        muscleListView.setItems(items);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Here we will fetch data from db
        populateListView();

    }
}
