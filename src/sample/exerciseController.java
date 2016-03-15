package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class exerciseController extends SceneController implements Initializable {

    public Label successLabel;
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
        boolean success = dbh.leggTilØvelse(exercisename, selectedMuscleGroup);
        System.out.println(success);
        if(!success){
            // Show a error label
            showFailLabel();
        }
        else{
            showSuccessLabel();
            System.err.println("Wrote successfully to the database!");
        }
    }

    public void showSuccessLabel(){
        successLabel.setStyle("-fx-text-fill: green");
        successLabel.setVisible(true);
        successLabel.setText("Successfully wrote to the database!");
    }
    public void showFailLabel(){

        successLabel.setStyle("-fx-text-fill: red");
        successLabel.setVisible(true);
        successLabel.setText("Couldn't write to the database!");
    }
    public void updateWarningLabel(ObservableValue<? extends String> property, String oldValue, String newValue){
        // Hide label
        successLabel.setVisible(false);
    }
}
