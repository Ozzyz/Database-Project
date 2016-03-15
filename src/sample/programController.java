package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Ozzy on 14.03.2016.
 */
public class programController extends SceneController implements Initializable{
    @FXML public ListView programListView;
    public TextField programNameTextField;
    public Label successLabel;
    @FXML ListView<String> exerciseListView;
    ObservableList<String> ExerciseItems;
    ObservableList<String> ProgramItems;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Here we will fetch data from db
        ArrayList<String> exercises = dbh.getExerciseNames();
        ExerciseItems = populateListView(exercises, exerciseListView);
        ProgramItems = populateListView(new ArrayList<>(), programListView);
    }

    public void addExerciseButtonClicked(ActionEvent actionEvent) {
        // Get what item the user has selected
        String selectedItem = exerciseListView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            System.out.println(selectedItem);
            ExerciseItems.remove(selectedItem);
            ProgramItems.add(selectedItem);
        }
    }

    public void removeExerciseButtonClicked(ActionEvent actionEvent) {
        // Get what item the user has selected
        String selectedItem = (String) programListView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            ExerciseItems.add(selectedItem);
            ProgramItems.remove(selectedItem);
        }

    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        // Get all selected items
        ObservableList selectedItems = programListView.getItems();
        String programName = programNameTextField.getText();
        ArrayList<String> exerciseList = new ArrayList<>();
        // Safely add all strings in selectedItems to exerciseList
        for(Object e: selectedItems){
            exerciseList.add(e.toString());
        }
        //write to db
        boolean success = dbh.setUpProgramØvelse(programName, exerciseList);
        if(success){
            showSuccessLabel();
        }
        else{
            showFailLabel();
        }
    }
    public void showFailLabel(){

        successLabel.setStyle("-fx-text-fill: red");
        successLabel.setVisible(true);
        successLabel.setText("Couldn't write to the database!");
    }
    public void showSuccessLabel(){
        successLabel.setStyle("-fx-text-fill: green");
        successLabel.setVisible(true);
        successLabel.setText("Successfully wrote to the database!");
    }


}
