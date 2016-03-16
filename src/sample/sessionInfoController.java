package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import javax.xml.transform.sax.SAXSource;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class sessionInfoController extends SceneController implements Initializable{

    public ComboBox exerciseComboBox;
    public TextField sett;
    public TextField reps;
    public TextField vekt;
    public Label response;
    private int currentSessionID;

    public void backButtonClickd(ActionEvent actionEvent) {
        super.backButtonClicked(actionEvent);
    }

    public void registerButtonClicked(ActionEvent actionEvent) {
        String setString = sett.getText();
        String repString = reps.getText();
        String weightString = vekt.getText();
        if(!setString.equals("") && !repString.equals("") && !weightString.equals("")){
            int set = Integer.parseInt(setString);
            int rep = Integer.parseInt(repString);
            int weight = Integer.parseInt(weightString);

            String selectedExercise = (String) exerciseComboBox.getSelectionModel().getSelectedItem();
            if (selectedExercise == null) {
                // Show error label
                response.setText("Velg en øvelse");
                response.setTextFill(Color.web("#ff0000"));
            } else {
                dbh.leggTilUtførelse(rep, set, weight, currentSessionID, selectedExercise);
                // Show success label
                response.setText(selectedExercise.toString() + " registrert!");
                response.setTextFill(Color.web("#00ff00"));
                //Reset all fields
                resetInputFields();

                //Exit view if finished
                if (exerciseComboBox.getItems().isEmpty()) {
                    try {
                        changeScene("sample.fxml", actionEvent);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        } else {
            response.setText("Fyll inn felter!");
            response.setTextFill(Color.web("#ff0000"));
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
