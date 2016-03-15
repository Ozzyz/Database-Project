package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class exerciseController extends SceneController implements Initializable {

    @FXML TextField exerciseName;
    @FXML ComboBox<String> gruppeComboBox;

    @Override
    public void initialize(URL url, ResourceBundle res) {
        ArrayList<String> exercises = dbh.getMuscleGroupNames();
        ObservableList<String> list = FXCollections.observableList(exercises);
        gruppeComboBox.setItems(list);
    }



    public void registerExercise(ActionEvent actionEvent) {
        String exercisename = exerciseName.getText();
        String selectedMuscleGroup = gruppeComboBox.getSelectionModel().getSelectedItem();
        dbh.leggTilØvelse(exercisename, selectedMuscleGroup);
    }
}
