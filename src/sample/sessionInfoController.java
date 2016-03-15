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

    public void backButtonClickd(ActionEvent actionEvent) {
        super.backButtonClicked(actionEvent);
    }

    public void registerButtonClicked(ActionEvent actionEvent) {
        //TODO Slette valgt øvelse og legge til data i utførelse i db
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ArrayList<String> exercises = dbh.getExercisesByProgramID(dbh.latestSession().get(1));
        ObservableList<String> list = FXCollections.observableList(exercises);
        exerciseComboBox.setItems(list);

    }
}
