package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class sessionInfoController extends SceneController implements Initializable{

    public ComboBox exerciseComboBox;
    public TextField sett;
    public TextField reps;
    public TextField vekt;
    private int currentSessionID;

    public void backButtonClickd(ActionEvent actionEvent) {
        super.backButtonClicked(actionEvent);
    }

    public void registerButtonClicked(ActionEvent actionEvent) {
        //TODO Slette valgt øvelse og legge til data i utførelse i db
        int set = Integer.parseInt(sett.getText());
        int rep = Integer.parseInt(reps.getText());
        int weight = Integer.parseInt(vekt.getText());
        String selectedExercise = (String) exerciseComboBox.getSelectionModel().getSelectedItem();
        if(selectedExercise.equals("Velg")){
            // Show error label
        }
        else{
            dbh.leggTilUtførelse(rep, set, weight, currentSessionID, selectedExercise);
            // Show success label

            //Reset all fields
            resetInputFields();
        }
    }

    public void resetInputFields(){
        sett.setText("");
        reps.setText("");
        vekt.setText("");
        //Remove the exercise we registered from combobox
        String selectedExercise = (String) exerciseComboBox.getSelectionModel().getSelectedItem();
        exerciseComboBox.getItems().removeAll(selectedExercise);
        exerciseComboBox.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ArrayList<Integer> sessionInfo = dbh.latestSession();
        int programID = sessionInfo.get(1);
        int sessionID = sessionInfo.get(0);
        currentSessionID = sessionID;
        ArrayList<String> exercises = dbh.getExercisesByProgramID(programID);
        ObservableList<String> list = FXCollections.observableList(exercises);
        System.out.println("Length of list:" + list.size());
        exerciseComboBox.setItems(list);

    }
}
