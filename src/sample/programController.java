package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    ObservableList<String> MuscleItems = FXCollections.observableArrayList (
            "Test1", "Test2", "ASD", "123");
    ObservableList<String> ProgramItems = FXCollections.observableArrayList();

    public void populateListView(){
        muscleListView.setItems(MuscleItems);
        programListView.setItems(ProgramItems);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Here we will fetch data from db
        populateListView();
    }

    public void addExerciseButtonClicked(ActionEvent actionEvent) {
        // Get what item the user has selected
        String selectedItem = muscleListView.getSelectionModel().getSelectedItem();
        MuscleItems.remove(selectedItem);
        ProgramItems.add(selectedItem);
    }

    public void removeExerciseButtonClicked(ActionEvent actionEvent) {

    }
}
